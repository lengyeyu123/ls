package com.han.ls;


import cn.hutool.http.HttpUtil;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class GetBidsTest {

    public static void main(String[] args) {
        AtomicInteger mThreadNum = new AtomicInteger(1);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20000),
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

        for (int i = 1; i < 2; i++) {
            GetBidsTask getBidsTask = new GetBidsTask(String.valueOf(i));
            executor.execute(getBidsTask);
        }

        executor.shutdown();
    }

    @AllArgsConstructor
    static class GetBidsTask implements Runnable {

        private String pageIndex;

        @Override
        public void run() {
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


            System.out.println(liELes);
        }
    }

}
