package com.han.ls;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class LsTests {

//     @Autowired
//     private AddressService addressService;
//
//     @Autowired
//     private UserService userService;
//
//     @Test
//     public void insertAddressAll() throws IOException {
//         String str = Files.asCharSource(new File("D:\\workspace\\ls\\src\\main\\resources\\address.txt"), Charsets.UTF_8).read();
//         List<Address> list = new ArrayList<>();
//         String[] split = str.split("\r\n");
//         ArrayList<String> strList = new ArrayList<>(Arrays.asList(split));
//         for (String subStr : strList) {
//             subStr = subStr.replace("\t\t\t\t\t\t", "");
//             String[] split1 = subStr.split("\t");
//             ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(split1));
//             System.out.println(stringArrayList);
//             Address address = new Address().setCode(stringArrayList.get(0).trim()).setName(stringArrayList.get(1).trim());
//             list.add(address);
//         }
//         System.out.println(list);
//         addressService.insertAll(list);
//     }
//
//     @Test
//     public void insertAllProvince() {
//         List<Address> list = addressService.all();
//         // 所有的省份
//         List<Province> provinceList = new ArrayList<>();
//         for (Address address : list) {
//             String code = address.getCode();
//             String substring = code.substring(2, code.length());
//             if (substring.equals("0000")) {
//                 provinceList.add(new Province().setCode(address.getCode()).setName(address.getName()));
//             }
//         }
//         addressService.insertAllProvince(provinceList);
//     }
//
//     @Test
//     public void insertAllCity() {
//         List<City> allCity = new ArrayList<>();
//
//         List<Address> addressList = addressService.all();
//         List<Province> allProvince = addressService.allProvince();
//         for (Province province : allProvince) {
//             String provinceCode = province.getCode();
//             String subPCode = provinceCode.substring(0, 2);
// //            String subCCode = provinceCode.substring(2, 4);
// //            String subCoCode = provinceCode.substring(4, 6);
//
//             List<City> cityList = new ArrayList<>();
//             for (Address address : addressList) {
//                 String addressCode = address.getCode();
//                 if (addressCode.substring(0, 2).equals(subPCode) && addressCode.substring(4, 6).equals("00") && !addressCode.substring(2, 4).equals("00")) {
//                     cityList.add(new City().setCode(addressCode).setName(address.getName()).setProvinceId(province.getId())
//                             .setProvinceCode(province.getCode()).setProvinceName(province.getName()));
//                 }
//             }
//             allCity.addAll(cityList);
//         }
//         addressService.insertAllCity(allCity);
//     }
//
//     @Test
//     public void insertAllCounty() {
//         List<County> allCounty = new ArrayList<>();
//
//         List<Address> addressList = addressService.all();
//         List<City> allCity = addressService.allCity();
//         for (City city : allCity) {
//             String cityCode = city.getCode();
//             String substring = cityCode.substring(0, 4);
//
//             List<County> countyList = new ArrayList<>();
//             for (Address address : addressList) {
//                 String addressCode = address.getCode();
//                 if (addressCode.substring(0, 4).equals(substring) && !addressCode.substring(4, 6).equals("00")) {
//                     countyList.add(new County().setCode(address.getCode()).setName(address.getName())
//                             .setProvinceId(city.getProvinceId()).setProvinceCode(city.getProvinceCode()).setProvinceName(city.getProvinceName())
//                             .setCityId(city.getId()).setCityCode(city.getCode()).setCityName(city.getName()));
//                 }
//             }
//             allCounty.addAll(countyList);
//         }
//
//         addressService.insertAllCounty(allCounty);
//     }
//
//     /**
//      * 插入两个用户
//      */
//     @Test
//     public void insertUser() {
//         List<User> list = new ArrayList<>();
//         Date now = new Date();
//         list.add(new User().setUserName("张三").setCreateTime(now));
//         list.add(new User().setUserName("李四").setCreateTime(now));
//         userService.insertUsers(list);
//     }
//
//     @Autowired
//     private TokenProperties tokenProperties;
//
//     /**
//      * 1#*（&&（88assa%$f%$#^^fdsg*#&@
//      * 异常 javax.xml.bind.DatatypeConverter.parseBase64Binary https://github.com/jwtk/jjwt/issues/285
//      */
//     @Test
//     public void createJwt() {
//         TokenProperties.TokenConfig tokenConfig = tokenProperties.getAtConfig();
//         Map<String, Object> map = new HashMap<>();
//         map.put("id", 1);
//         String secret = tokenConfig.getSecret();
//         String token = Jwts.builder()
//                 .setClaims(map)
//                 .setExpiration(new Date(System.currentTimeMillis() + tokenConfig.getExpireTime() * 60 * 1000L))
//                 .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
//         System.out.println(token);
//
//         Claims body = Jwts.parser()
//                 .setSigningKey(tokenConfig.getSecret().getBytes())
//                 .parseClaimsJwt(token)
//                 .getBody();
//         System.out.println(body);
//     }

    /**
     * https://www.cnblogs.com/chenny7/p/13187826.html
     * 测试多线程
     */
    public static void main(String[] args) {
        AtomicInteger mThreadNum = new AtomicInteger(1);

        // TODO 吞吐量
        // corePoolSize 核心线程数，一旦创建将不会在释放
        // maximumPoolSize 最大线程数，如果最大线程数等于核心线程数，则无法创建非核心线程，如果非核心线程处于空闲时，超过设置的空闲时间，则将被回收，释放占用的资源
        // keepAliveTime 也就是当线程空闲时，所允许保存的最大时间，超过这个时间，线程将被释放销毁，但只针对于非核心线程。
        // workQueue 任务队列，用于保存的的古代执行的任务的阻塞队列，可以选择以下几个阻塞队列
        // 1. ArrayBlockingQueue 一个基于数组结构的有界阻塞队列，必须设置容量。此队列按 FIFO（先进先出）原则对元素进行排序。
        // 2. LinkedBlockingQueue 一个基于链表结构的阻塞队列，可以设置容量，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。
        // 3. SynchronousQueue 一个不存储元素的阻塞队列。每个插入offer操作必须等到另一个线程调用移除poll操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue。
        // 4. PriorityBlockingQueue 一个具有优先级的无限阻塞队列。
        // threadFactory 线程工厂，用于创建线程
        // RejectedExecutionHandler 拒绝策略，当线程边界和队列容量已经达到最大时，用于处理阻塞的线程。
        // 1. AbortPolicy：默认策略，抛出异常RejectedExecutionException拒绝提交任务；
        // 2. CallerRunsPolicy：由调用execute方法提交任务的线程来执行这个任务；
        // 3. DiscardPolicy：直接抛弃任务，不做任何处理；
        // 4. DiscardOldestPolicy：去除任务队列中的第一个任务，重新提交；
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
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

        for (int i = 1; i < 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            // 提交任务有两个方法
            // 1. execute(Runnable)，无返回值；
            // 2. submit(Callable<T>)，有返回值；
            executor.execute(task);
        }

        executor.shutdown();
    }

    @AllArgsConstructor
    static class MyTask implements Runnable {

        private String name;

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(this.toString() + " is running!");
            Thread.sleep(2000);
        }

        @Override
        public String toString() {
            return "MyTask [name + " + name + "]";
        }
    }

}
