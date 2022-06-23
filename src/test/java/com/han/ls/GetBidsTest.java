package com.han.ls;


import cn.hutool.http.HttpUtil;
import com.google.common.collect.Lists;
import com.han.ls.common.utils.DateUtils;
import com.han.ls.common.utils.JsonUtils;
import com.han.ls.project.domain.Bid;
import com.han.ls.project.mapper.BidMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class GetBidsTest {

    @Autowired
    private BidMapper bidMapper;

    @Test
    public void insetBids() throws ExecutionException, InterruptedException {
        AtomicInteger mThreadNum = new AtomicInteger(1);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
                        System.out.println(t.getName() + " has bean created");
                        return t;
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.err.println(r.toString() + " rejected");
                    }
                }
        );

        // 预启动所有核心线程
        executor.prestartAllCoreThreads();

        for (int i = 1; i < 11; i++) {
            GetBidsTask getBidsTask = new GetBidsTask(String.valueOf(i));
            Future<List<Bid>> submit = executor.submit(getBidsTask);
            List<Bid> bids = submit.get();
            bidMapper.insertAll(bids);
        }

        executor.shutdown();
    }

    @AllArgsConstructor
    static class GetBidsTask implements Callable {

        private String pageIndex;

        @Override
        public List<Bid> call() throws Exception {
            Map<String, Object> params = new HashMap<>();
            params.put("searchtype", "1");
            // 页码
            params.put("page_index", this.pageIndex);
            // 类别 所有 中央 地方
            params.put("bidSort", "2");
            // 品目 所有 货物类 工程类 服务类
            params.put("pinMu", "3");
            // 类型 所有类型 公开招标 询价公告 竞争性谈判 单一来源 资格预审 邀请公告 中标公告 更正公告 其他公告 竞争性磋商 成交公告 终止公告
            params.put("bidType", "1");
            params.put("dbselect", "bidx");
            // 起止日期 不能超过365天
            params.put("start_time", "2021%3A12%3A22");
            params.put("end_time", "2022%3A06%3A23");
            params.put("timeType", "5");
            params.put("pppStatus", "0");
            // &bidSort=0&buyerName=&projectId=&pinMu=2&bidType=1&dbselect=bidx&kw=&start_time=2022%3A05%3A22&end_time=2022%3A06%3A22&timeType=3&displayZone=&zoneId=&pppStatus=&agentName=

            String htmlStr = HttpUtil.get("https://search.ccgp.gov.cn/bxsearch", params);
            Document doc = Jsoup.parse(htmlStr);
            Elements liELes = doc.select("ul.vT-srch-result-list-bid > li");

            Date now = new Date();
            List<Bid> insertList = new ArrayList<>();
            for (Element liEle : liELes) {
                Bid insert = new Bid();
                insert.setTitle(liEle.select("a").text().trim());
                String spanText = liEle.select("span").text().trim();
                String[] split = spanText.split("\\|");
                String uploadTimeStr = split[0].trim();
                Date uploadTime = DateUtils.dateTime("yyyy.MM.dd HH:mm:ss", uploadTimeStr);
                insert.setUploadTime(uploadTime);
                insert.setBuyer(split[1].trim().replace("采购人：", ""));
                insert.setAgentCompany(split[2].trim().replace("代理机构：", ""));
                insert.setType(liEle.select("span > strong").last().text().trim());
                insert.setBidUrl(liEle.select("a").first().attr("href").trim());
                insert.setAddress(liEle.select("span > a").text().trim());
                insert.setCreateTime(now);
                insertList.add(insert);
            }
            Thread.sleep(RandomUtils.nextInt(6, 10) * 1000L);
            return insertList;
        }
    }

    @Test
    public void getSoftwareBids() {
        List<Bid> bids = bidMapper.selectLikeType(Lists.newArrayList("软件", "运维", "信息"));
        System.out.println(JsonUtils.toJson(bids));
    }

}
