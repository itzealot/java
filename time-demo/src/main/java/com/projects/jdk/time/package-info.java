package com.projects.jdk.time;
/**
 * 1. java.time包：这是新的Java日期/时间API的基础包，所有的主要基础类都是这个包的一部分，如：LocalDate, LocalTime,
 * LocalDateTime, Instant, Period, Duration等等。
 * 所有这些类都是不可变的和线程安全的，在绝大多数情况下，这些类能够有效地处理一些公共的需求。<br />
 * 
 * 2. java.time.chrono包：这个包为非ISO的日历系统定义了一些泛化的API，
 * 我们可以扩展AbstractChronology类来创建自己的日历系统。<br />
 * 
 * 3. java.time.format包：这个包包含能够格式化和解析日期时间对象的类，在绝大多数情况下，我们不应该直接使用它们，因为java.
 * time包中相应的类已经提供了格式化和解析的方法。<br />
 * 
 * 4. java.time.temporal包：这个包包含一些时态对象，我们可以用其找出关于日期/时间对象的某个特定日期或时间，比如说，
 * 可以找到某月的第一天或最后一天。你可以非常容易地认出这些方法，因为它们都具有“withXXX”的格式。<br />
 * 
 * 5. java.time.zone包：这个包包含支持不同时区以及相关规则的类。<br />
 */
