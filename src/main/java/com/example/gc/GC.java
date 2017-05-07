package com.example.gc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 17-5-6.
 */
public class GC {
    public static final String json="Java HotSpot(TM) 64-Bit Server VM (25.121-b13) for linux-amd64 JRE (1.8.0_121-b13), built on Dec 12 2016 16:36:53 by \"java_re\" with gcc 4.3.0 20080428 (Red Hat 4.3.0-8)\n" +
            "Memory: 4k page, physical 11996016k(5941916k free), swap 15637500k(15637500k free)\n" +
            "CommandLine flags: -XX:InitialHeapSize=2147483648 -XX:MaxHeapSize=2147483648 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:ThreadStackSize=512 -XX:+UseAdaptiveSizePolicy -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC \n" +
            "2017-05-07T13:58:35.605+0800: 0.247: [GC (Allocation Failure)  524800K->696K(2010112K), 0.0019015 secs]\n" +
            "2017-05-07T13:58:35.924+0800: 0.565: [GC (Allocation Failure)  525496K->3968K(2010112K), 0.0227037 secs]\n" +
            "2017-05-07T13:58:36.309+0800: 0.951: [GC (Allocation Failure)  528768K->9600K(2010112K), 0.0663783 secs]\n" +
            "2017-05-07T13:58:36.761+0800: 1.402: [GC (Allocation Failure)  534400K->17904K(2010112K), 0.1244402 secs]\n" +
            "2017-05-07T13:58:37.094+0800: 1.735: [GC (Allocation Failure)  542704K->25272K(2010112K), 0.1837699 secs]\n" +
            "2017-05-07T13:58:37.521+0800: 2.162: [GC (Allocation Failure)  550072K->39104K(2041856K), 0.3219012 secs]\n" +
            "2017-05-07T13:58:38.103+0800: 2.744: [GC (Allocation Failure)  652992K->39336K(2038784K), 0.2618801 secs]\n" +
            "2017-05-07T13:58:38.631+0800: 3.272: [GC (Allocation Failure)  653224K->39392K(2055168K), 0.1867348 secs]\n" +
            "2017-05-07T13:58:39.086+0800: 3.727: [GC (Allocation Failure)  652256K->39448K(2053120K), 0.0794968 secs]\n" +
            "2017-05-07T13:58:39.490+0800: 4.131: [GC (Allocation Failure)  652312K->39536K(2055168K), 0.0231235 secs]\n" +
            "2017-05-07T13:58:39.792+0800: 4.433: [GC (Allocation Failure)  654448K->39440K(2055168K), 0.0023392 secs]\n" +
            "2017-05-07T13:58:40.068+0800: 4.709: [GC (Allocation Failure)  654352K->39472K(2056704K), 0.0008502 secs]\n" +
            "2017-05-07T13:58:40.298+0800: 4.939: [GC (Allocation Failure)  656432K->39536K(2055680K), 0.0008793 secs]\n" +
            "2017-05-07T13:58:40.500+0800: 5.141: [GC (Allocation Failure)  656496K->39536K(2058752K), 0.0007163 secs]\n" +
            "2017-05-07T13:58:40.708+0800: 5.349: [GC (Allocation Failure)  660592K->39536K(2057728K), 0.0008160 secs]\n" +
            "2017-05-07T13:58:40.914+0800: 5.555: [GC (Allocation Failure)  660592K->39536K(2061824K), 0.0007469 secs]\n" +
            "2017-05-07T13:58:41.121+0800: 5.762: [GC (Allocation Failure)  666224K->39536K(2060288K), 0.0006936 secs]\n" +
            "2017-05-07T13:58:41.329+0800: 5.971: [GC (Allocation Failure)  666224K->39472K(2064896K), 0.0011747 secs]\n" +
            "2017-05-07T13:58:41.544+0800: 6.186: [GC (Allocation Failure)  672304K->39536K(2063360K), 0.0007159 secs]\n" +
            "2017-05-07T13:58:41.752+0800: 6.393: [GC (Allocation Failure)  672368K->39440K(2068480K), 0.0013637 secs]\n" +
            "2017-05-07T13:58:41.968+0800: 6.609: [GC (Allocation Failure)  678928K->39504K(2066432K), 0.0008665 secs]\n" +
            "2017-05-07T13:58:42.181+0800: 6.822: [GC (Allocation Failure)  678992K->39504K(2071552K), 0.0007850 secs]\n" +
            "2017-05-07T13:58:42.399+0800: 7.040: [GC (Allocation Failure)  685648K->39536K(2070016K), 0.0007002 secs]\n" +
            "2017-05-07T13:58:42.612+0800: 7.253: [GC (Allocation Failure)  685680K->39536K(2074624K), 0.0007115 secs]\n" +
            "2017-05-07T13:58:42.829+0800: 7.471: [GC (Allocation Failure)  691824K->39472K(2073088K), 0.0011606 secs]\n" +
            "2017-05-07T13:58:43.044+0800: 7.685: [GC (Allocation Failure)  691760K->39536K(2077184K), 0.0007538 secs]\n" +
            "2017-05-07T13:58:43.263+0800: 7.904: [GC (Allocation Failure)  696944K->39536K(2075648K), 0.0007320 secs]\n" +
            "2017-05-07T13:58:43.484+0800: 8.125: [GC (Allocation Failure)  696944K->39536K(2079744K), 0.0007307 secs]\n" +
            "2017-05-07T13:58:43.703+0800: 8.344: [GC (Allocation Failure)  702576K->39536K(2078720K), 0.0007639 secs]\n" +
            "2017-05-07T13:58:43.924+0800: 8.565: [GC (Allocation Failure)  702576K->39504K(2081792K), 0.0007738 secs]\n" +
            "2017-05-07T13:58:44.143+0800: 8.784: [GC (Allocation Failure)  706640K->39472K(2080768K), 0.0008939 secs]\n" +
            "2017-05-07T13:58:44.366+0800: 9.007: [GC (Allocation Failure)  706608K->39472K(2083840K), 0.0008225 secs]\n" +
            "2017-05-07T13:58:44.588+0800: 9.229: [GC (Allocation Failure)  710704K->39440K(2082816K), 0.0012613 secs]\n" +
            "2017-05-07T13:58:44.878+0800: 9.519: [GC (Allocation Failure)  710672K->39440K(2085888K), 0.0014599 secs]\n" +
            "2017-05-07T13:58:45.116+0800: 9.757: [GC (Allocation Failure)  714768K->39472K(2084864K), 0.0011124 secs]\n" +
            "2017-05-07T13:58:45.339+0800: 9.980: [GC (Allocation Failure)  714800K->39472K(2087424K), 0.0007997 secs]\n" +
            "2017-05-07T13:58:45.568+0800: 10.209: [GC (Allocation Failure)  717872K->39536K(2086400K), 0.0006931 secs]\n" +
            "2017-05-07T13:58:45.794+0800: 10.435: [GC (Allocation Failure)  717936K->39440K(2088960K), 0.0012720 secs]\n" +
            "2017-05-07T13:58:46.023+0800: 10.664: [GC (Allocation Failure)  720912K->39536K(2087936K), 0.0008135 secs]\n" +
            "2017-05-07T13:58:46.248+0800: 10.889: [GC (Allocation Failure)  721008K->39536K(2089984K), 0.0006912 secs]\n" +
            "2017-05-07T13:58:46.476+0800: 11.118: [GC (Allocation Failure)  723568K->39536K(2089472K), 0.0007276 secs]\n" +
            "2017-05-07T13:58:46.703+0800: 11.344: [GC (Allocation Failure)  723568K->39536K(2091008K), 0.0006801 secs]\n" +
            "2017-05-07T13:58:46.928+0800: 11.569: [GC (Allocation Failure)  725616K->39472K(2090496K), 0.0008521 secs]\n" +
            "2017-05-07T13:58:47.156+0800: 11.797: [GC (Allocation Failure)  725552K->39536K(2092032K), 0.0006997 secs]\n" +
            "2017-05-07T13:58:47.386+0800: 12.028: [GC (Allocation Failure)  727664K->39536K(2091520K), 0.0007503 secs]\n" +
            "2017-05-07T13:58:47.614+0800: 12.256: [GC (Allocation Failure)  727664K->39504K(2092544K), 0.0008581 secs]\n" +
            "2017-05-07T13:58:47.841+0800: 12.482: [GC (Allocation Failure)  728656K->39440K(2092032K), 0.0013099 secs]\n" +
            "2017-05-07T13:58:48.072+0800: 12.713: [GC (Allocation Failure)  728592K->39440K(2093056K), 0.0012687 secs]\n" +
            "2017-05-07T13:58:48.301+0800: 12.942: [GC (Allocation Failure)  730128K->39536K(2093056K), 0.0008060 secs]\n" +
            "2017-05-07T13:58:48.529+0800: 13.171: [GC (Allocation Failure)  730224K->39472K(2093568K), 0.0010712 secs]\n" +
            "2017-05-07T13:58:48.763+0800: 13.404: [GC (Allocation Failure)  731184K->39536K(2093568K), 0.0007089 secs]\n" +
            "2017-05-07T13:58:49.000+0800: 13.641: [GC (Allocation Failure)  731248K->39504K(2094080K), 0.0007902 secs]\n" +
            "2017-05-07T13:58:49.241+0800: 13.882: [GC (Allocation Failure)  732240K->39440K(2094080K), 0.0013618 secs]\n" +
            "2017-05-07T13:58:49.482+0800: 14.123: [GC (Allocation Failure)  732176K->39440K(2094592K), 0.0027085 secs]\n" +
            "2017-05-07T13:58:49.970+0800: 14.611: [GC (Allocation Failure)  733200K->39448K(2094592K), 0.0156047 secs]\n" +
            "2017-05-07T13:58:50.246+0800: 14.887: [GC (Allocation Failure)  733208K->39448K(2095104K), 0.0013490 secs]\n" +
            "2017-05-07T13:58:50.478+0800: 15.120: [GC (Allocation Failure)  733720K->39512K(2094592K), 0.0007840 secs]\n" +
            "2017-05-07T13:58:50.714+0800: 15.355: [GC (Allocation Failure)  733784K->39448K(2095104K), 0.0040985 secs]\n" +
            "2017-05-07T13:58:50.990+0800: 15.632: [GC (Allocation Failure)  734232K->39480K(2095104K), 0.0011114 secs]\n" +
            "2017-05-07T13:58:51.222+0800: 15.863: [GC (Allocation Failure)  734264K->39448K(2095616K), 0.0012969 secs]\n" +
            "2017-05-07T13:58:51.452+0800: 16.093: [GC (Allocation Failure)  735256K->39512K(2095616K), 0.0007954 secs]\n" +
            "2017-05-07T13:58:51.754+0800: 16.395: [GC (Allocation Failure)  735320K->39584K(2095616K), 0.0014734 secs]\n" +
            "2017-05-07T13:58:52.180+0800: 16.821: [GC (Allocation Failure)  735392K->39632K(2095616K), 0.0017796 secs]\n" +
            "2017-05-07T13:58:52.479+0800: 17.120: [GC (Allocation Failure)  735440K->39696K(2096128K), 0.0011670 secs]\n" +
            "2017-05-07T13:58:52.730+0800: 17.371: [GC (Allocation Failure)  736016K->39824K(2095616K), 0.0009563 secs]\n" +
            "2017-05-07T13:58:52.978+0800: 17.620: [GC (Allocation Failure)  736144K->39864K(2096128K), 0.0011447 secs]\n" +
            "2017-05-07T13:58:53.235+0800: 17.876: [GC (Allocation Failure)  736696K->39912K(2096128K), 0.0011045 secs]\n" +
            "2017-05-07T13:58:53.479+0800: 18.120: [GC (Allocation Failure)  736744K->40000K(2096128K), 0.0017987 secs]\n" +
            "2017-05-07T13:58:53.727+0800: 18.368: [GC (Allocation Failure)  736832K->40072K(2096128K), 0.0011443 secs]\n" +
            "2017-05-07T13:58:54.002+0800: 18.643: [GC (Allocation Failure)  736904K->40208K(2096128K), 0.0026075 secs]\n" +
            "2017-05-07T13:58:54.260+0800: 18.901: [GC (Allocation Failure)  737040K->40328K(2096128K), 0.0036955 secs]\n" +
            "2017-05-07T13:58:54.524+0800: 19.165: [GC (Allocation Failure)  737160K->40448K(2096128K), 0.0023716 secs]\n" +
            "2017-05-07T13:58:54.771+0800: 19.412: [GC (Allocation Failure)  737280K->40760K(2096128K), 0.0011942 secs]\n" +
            "2017-05-07T13:58:55.031+0800: 19.672: [GC (Allocation Failure)  737592K->40844K(2096128K), 0.0018505 secs]\n" +
            "2017-05-07T13:58:55.303+0800: 19.944: [GC (Allocation Failure)  737676K->40892K(2096128K), 0.0016189 secs]\n" +
            "2017-05-07T13:58:55.548+0800: 20.189: [GC (Allocation Failure)  737724K->41092K(2096640K), 0.0014733 secs]\n" +
            "2017-05-07T13:58:55.808+0800: 20.449: [GC (Allocation Failure)  738948K->41116K(2096640K), 0.0017988 secs]\n" +
            "2017-05-07T13:58:56.155+0800: 20.796: [GC (Allocation Failure)  738972K->41100K(2096640K), 0.0168646 secs]\n" +
            "2017-05-07T13:58:56.628+0800: 21.269: [GC (Allocation Failure)  738956K->41236K(2096640K), 0.0012782 secs]\n" +
            "2017-05-07T13:58:56.859+0800: 21.500: [GC (Allocation Failure)  739092K->41172K(2096640K), 0.0014499 secs]\n" +
            "2017-05-07T13:58:57.099+0800: 21.740: [GC (Allocation Failure)  739028K->41172K(2096640K), 0.0011745 secs]\n" +
            "2017-05-07T13:58:57.354+0800: 21.995: [GC (Allocation Failure)  739028K->41300K(2096640K), 0.0023358 secs]\n" +
            "2017-05-07T13:58:57.606+0800: 22.247: [GC (Allocation Failure)  739156K->41236K(2096640K), 0.0015130 secs]\n" +
            "2017-05-07T13:58:57.920+0800: 22.561: [GC (Allocation Failure)  739092K->41220K(2096640K), 0.0023960 secs]\n" +
            "2017-05-07T13:58:58.208+0800: 22.849: [GC (Allocation Failure)  739076K->41228K(2096640K), 0.0024502 secs]\n" +
            "2017-05-07T13:58:58.609+0800: 23.250: [GC (Allocation Failure)  739084K->41292K(2096640K), 0.0019965 secs]\n" +
            "2017-05-07T13:58:58.892+0800: 23.533: [GC (Allocation Failure)  739148K->41332K(2096640K), 0.0047880 secs]\n" +
            "2017-05-07T13:58:59.230+0800: 23.871: [GC (Allocation Failure)  739188K->41308K(2096640K), 0.0014601 secs]\n" +
            "2017-05-07T13:58:59.479+0800: 24.120: [GC (Allocation Failure)  739164K->41356K(2096640K), 0.0018087 secs]\n" +
            "2017-05-07T13:58:59.752+0800: 24.393: [GC (Allocation Failure)  739212K->41356K(2096640K), 0.0011106 secs]\n" +
            "2017-05-07T13:59:00.012+0800: 24.654: [GC (Allocation Failure)  739212K->41388K(2096640K), 0.0016623 secs]\n" +
            "2017-05-07T13:59:00.341+0800: 24.982: [GC (Allocation Failure)  739244K->41300K(2096640K), 0.0019103 secs]\n" +
            "2017-05-07T13:59:00.580+0800: 25.222: [GC (Allocation Failure)  739156K->41412K(2096640K), 0.0009002 secs]\n" +
            "2017-05-07T13:59:00.813+0800: 25.455: [GC (Allocation Failure)  739268K->41412K(2096640K), 0.0009998 secs]\n" +
            "2017-05-07T13:59:01.109+0800: 25.750: [GC (Allocation Failure)  739268K->41316K(2096640K), 0.0018992 secs]\n" +
            "2017-05-07T13:59:01.407+0800: 26.048: [GC (Allocation Failure)  739172K->41404K(2096640K), 0.0010136 secs]\n" +
            "2017-05-07T13:59:01.657+0800: 26.298: [GC (Allocation Failure)  739260K->41372K(2096640K), 0.0011865 secs]\n" +
            "2017-05-07T13:59:01.933+0800: 26.575: [GC (Allocation Failure)  739228K->41340K(2096640K), 0.0021098 secs]\n" +
            "2017-05-07T13:59:02.231+0800: 26.872: [GC (Allocation Failure)  739196K->41388K(2096640K), 0.0015301 secs]\n" +
            "2017-05-07T13:59:02.469+0800: 27.110: [GC (Allocation Failure)  739244K->41396K(2096640K), 0.0014040 secs]\n" +
            "2017-05-07T13:59:02.698+0800: 27.339: [GC (Allocation Failure)  739252K->41396K(2096640K), 0.0009847 secs]\n" +
            "2017-05-07T13:59:02.945+0800: 27.586: [GC (Allocation Failure)  739252K->41492K(2096640K), 0.0017864 secs]\n" +
            "2017-05-07T13:59:03.259+0800: 27.900: [GC (Allocation Failure)  739348K->41412K(2096640K), 0.0013313 secs]\n" +
            "2017-05-07T13:59:03.488+0800: 28.129: [GC (Allocation Failure)  739268K->41444K(2096640K), 0.0010598 secs]\n" +
            "2017-05-07T13:59:03.720+0800: 28.362: [GC (Allocation Failure)  739300K->41412K(2096640K), 0.0014013 secs]\n" +
            "2017-05-07T13:59:03.972+0800: 28.613: [GC (Allocation Failure)  739268K->41444K(2096640K), 0.0017241 secs]\n" +
            "2017-05-07T13:59:04.262+0800: 28.903: [GC (Allocation Failure)  739300K->41388K(2096640K), 0.0015899 secs]\n" +
            "2017-05-07T13:59:04.489+0800: 29.130: [GC (Allocation Failure)  739244K->41484K(2096640K), 0.0009396 secs]\n" +
            "2017-05-07T13:59:04.720+0800: 29.361: [GC (Allocation Failure)  739340K->41420K(2096640K), 0.0010667 secs]\n" +
            "2017-05-07T13:59:05.005+0800: 29.646: [GC (Allocation Failure)  739276K->41428K(2096640K), 0.0042017 secs]\n" +
            "2017-05-07T13:59:05.304+0800: 29.945: [GC (Allocation Failure)  739284K->41444K(2096640K), 0.0012055 secs]\n" +
            "2017-05-07T13:59:05.532+0800: 30.173: [GC (Allocation Failure)  739300K->41508K(2096640K), 0.0011879 secs]\n" +
            "2017-05-07T13:59:05.760+0800: 30.401: [GC (Allocation Failure)  739364K->41444K(2096640K), 0.0012032 secs]\n" +
            "2017-05-07T13:59:06.075+0800: 30.716: [GC (Allocation Failure)  739300K->41444K(2096640K), 0.0020183 secs]\n" +
            "2017-05-07T13:59:06.327+0800: 30.968: [GC (Allocation Failure)  739300K->41460K(2096640K), 0.0012505 secs]\n" +
            "2017-05-07T13:59:06.554+0800: 31.195: [GC (Allocation Failure)  739316K->41460K(2096640K), 0.0009358 secs]\n" +
            "2017-05-07T13:59:06.791+0800: 31.432: [GC (Allocation Failure)  739316K->41524K(2096640K), 0.0010603 secs]\n" +
            "2017-05-07T13:59:07.148+0800: 31.789: [GC (Allocation Failure)  739380K->41468K(2096640K), 0.0026878 secs]\n" +
            "2017-05-07T13:59:07.451+0800: 32.093: [GC (Allocation Failure)  739324K->41492K(2096640K), 0.0010479 secs]\n" +
            "2017-05-07T13:59:07.713+0800: 32.354: [GC (Allocation Failure)  739348K->41460K(2096640K), 0.0019095 secs]\n" +
            "2017-05-07T13:59:08.060+0800: 32.701: [GC (Allocation Failure)  739316K->41492K(2096640K), 0.0049861 secs]\n" +
            "2017-05-07T13:59:08.327+0800: 32.968: [GC (Allocation Failure)  739348K->41588K(2096640K), 0.0009647 secs]\n" +
            "2017-05-07T13:59:08.591+0800: 33.232: [GC (Allocation Failure)  739444K->41524K(2096640K), 0.0013328 secs]\n" +
            "2017-05-07T13:59:08.868+0800: 33.509: [GC (Allocation Failure)  739380K->41524K(2096640K), 0.0014146 secs]\n" +
            "2017-05-07T13:59:09.268+0800: 33.910: [GC (Allocation Failure)  739380K->41524K(2096640K), 0.0015323 secs]\n" +
            "2017-05-07T13:59:09.546+0800: 34.188: [GC (Allocation Failure)  739380K->41500K(2096640K), 0.0034883 secs]\n" +
            "2017-05-07T13:59:09.804+0800: 34.445: [GC (Allocation Failure)  739356K->41596K(2096640K), 0.0029473 secs]\n" +
            "2017-05-07T13:59:10.167+0800: 34.808: [GC (Allocation Failure)  739452K->41596K(2096640K), 0.0021929 secs]\n" +
            "2017-05-07T13:59:10.447+0800: 35.088: [GC (Allocation Failure)  739452K->41628K(2096640K), 0.0009781 secs]\n" +
            "2017-05-07T13:59:10.687+0800: 35.328: [GC (Allocation Failure)  739484K->41564K(2096640K), 0.0013222 secs]\n" +
            "2017-05-07T13:59:10.945+0800: 35.587: [GC (Allocation Failure)  739420K->41604K(2096640K), 0.0031763 secs]\n" +
            "2017-05-07T13:59:11.267+0800: 35.908: [GC (Allocation Failure)  739460K->41588K(2096640K), 0.0014306 secs]\n" +
            "2017-05-07T13:59:11.506+0800: 36.147: [GC (Allocation Failure)  739444K->41652K(2096640K), 0.0012456 secs]\n" +
            "2017-05-07T13:59:11.757+0800: 36.398: [GC (Allocation Failure)  739508K->41588K(2096640K), 0.0012342 secs]\n" +
            "2017-05-07T13:59:12.112+0800: 36.753: [GC (Allocation Failure)  739444K->41556K(2096640K), 0.0025332 secs]\n" +
            "2017-05-07T13:59:12.377+0800: 37.018: [GC (Allocation Failure)  739412K->41612K(2096640K), 0.0011352 secs]\n" +
            "2017-05-07T13:59:12.642+0800: 37.283: [GC (Allocation Failure)  739468K->41612K(2096640K), 0.0012800 secs]\n" +
            "2017-05-07T13:59:12.932+0800: 37.573: [GC (Allocation Failure)  739468K->41580K(2096640K), 0.0064902 secs]\n" +
            "2017-05-07T13:59:13.263+0800: 37.905: [GC (Allocation Failure)  739436K->41628K(2096640K), 0.0014163 secs]\n" +
            "2017-05-07T13:59:13.491+0800: 38.132: [GC (Allocation Failure)  739484K->41700K(2096640K), 0.0010960 secs]\n" +
            "2017-05-07T13:59:13.723+0800: 38.364: [GC (Allocation Failure)  739556K->41700K(2096640K), 0.0009005 secs]\n" +
            "2017-05-07T13:59:13.977+0800: 38.619: [GC (Allocation Failure)  739556K->41644K(2096640K), 0.0013742 secs]\n" +
            "2017-05-07T13:59:14.265+0800: 38.906: [GC (Allocation Failure)  739500K->41740K(2096640K), 0.0009783 secs]\n" +
            "2017-05-07T13:59:14.493+0800: 39.134: [GC (Allocation Failure)  739596K->41708K(2096640K), 0.0010709 secs]\n" +
            "2017-05-07T13:59:14.723+0800: 39.364: [GC (Allocation Failure)  739564K->41740K(2096640K), 0.0009058 secs]\n" +
            "2017-05-07T13:59:14.995+0800: 39.636: [GC (Allocation Failure)  739596K->41708K(2096640K), 0.0015632 secs]\n" +
            "2017-05-07T13:59:15.273+0800: 39.914: [GC (Allocation Failure)  739564K->41772K(2096640K), 0.0009616 secs]\n" +
            "2017-05-07T13:59:15.508+0800: 40.149: [GC (Allocation Failure)  739628K->41772K(2096640K), 0.0010634 secs]\n" +
            "2017-05-07T13:59:15.736+0800: 40.377: [GC (Allocation Failure)  739628K->41772K(2096640K), 0.0009831 secs]\n" +
            "2017-05-07T13:59:16.009+0800: 40.650: [GC (Allocation Failure)  739628K->41676K(2096640K), 0.0024778 secs]\n" +
            "2017-05-07T13:59:16.278+0800: 40.919: [GC (Allocation Failure)  739532K->41804K(2096640K), 0.0009789 secs]\n" +
            "2017-05-07T13:59:16.508+0800: 41.150: [GC (Allocation Failure)  739660K->41708K(2096640K), 0.0016090 secs]\n" +
            "2017-05-07T13:59:16.736+0800: 41.377: [GC (Allocation Failure)  739564K->41772K(2096640K), 0.0009883 secs]\n" +
            "2017-05-07T13:59:16.997+0800: 41.638: [GC (Allocation Failure)  739628K->41708K(2096640K), 0.0024664 secs]\n" +
            "2017-05-07T13:59:17.284+0800: 41.925: [GC (Allocation Failure)  739564K->41756K(2096640K), 0.0011535 secs]\n" +
            "2017-05-07T13:59:17.516+0800: 42.158: [GC (Allocation Failure)  739612K->41788K(2096640K), 0.0011458 secs]\n" +
            "2017-05-07T13:59:17.744+0800: 42.385: [GC (Allocation Failure)  739644K->41724K(2096640K), 0.0016911 secs]\n" +
            "2017-05-07T13:59:18.032+0800: 42.673: [GC (Allocation Failure)  739580K->41788K(2096640K), 0.0036691 secs]\n" +
            "2017-05-07T13:59:18.303+0800: 42.944: [GC (Allocation Failure)  739644K->41852K(2096640K), 0.0010335 secs]\n" +
            "2017-05-07T13:59:18.534+0800: 43.176: [GC (Allocation Failure)  739708K->41788K(2096640K), 0.0011847 secs]\n" +
            "2017-05-07T13:59:18.763+0800: 43.404: [GC (Allocation Failure)  739644K->41788K(2096640K), 0.0012883 secs]\n" +
            "2017-05-07T13:59:19.052+0800: 43.694: [GC (Allocation Failure)  739644K->41916K(2096640K), 0.0023287 secs]\n" +
            "2017-05-07T13:59:19.314+0800: 43.955: [GC (Allocation Failure)  739772K->41764K(2096640K), 0.0017604 secs]\n" +
            "2017-05-07T13:59:19.545+0800: 44.186: [GC (Allocation Failure)  739620K->41764K(2096640K), 0.0017023 secs]\n" +
            "2017-05-07T13:59:19.773+0800: 44.415: [GC (Allocation Failure)  739620K->41860K(2096640K), 0.0009865 secs]\n" +
            "2017-05-07T13:59:20.025+0800: 44.666: [GC (Allocation Failure)  739716K->41804K(2096640K), 0.0034021 secs]\n" +
            "2017-05-07T13:59:20.320+0800: 44.961: [GC (Allocation Failure)  739660K->41900K(2096640K), 0.0010402 secs]\n" +
            "2017-05-07T13:59:20.550+0800: 45.191: [GC (Allocation Failure)  739756K->41868K(2096640K), 0.0011247 secs]\n" +
            "2017-05-07T13:59:20.778+0800: 45.420: [GC (Allocation Failure)  739724K->41868K(2096640K), 0.0010453 secs]\n" +
            "2017-05-07T13:59:21.056+0800: 45.697: [GC (Allocation Failure)  739724K->41836K(2096640K), 0.0016684 secs]\n" +
            "2017-05-07T13:59:21.322+0800: 45.964: [GC (Allocation Failure)  739692K->41868K(2096640K), 0.0010199 secs]\n" +
            "2017-05-07T13:59:21.555+0800: 46.196: [GC (Allocation Failure)  739724K->41860K(2096640K), 0.0011892 secs]\n" +
            "2017-05-07T13:59:21.784+0800: 46.425: [GC (Allocation Failure)  739716K->41860K(2096640K), 0.0010587 secs]\n" +
            "2017-05-07T13:59:22.097+0800: 46.738: [GC (Allocation Failure)  739716K->41868K(2096640K), 0.0020378 secs]\n" +
            "2017-05-07T13:59:22.353+0800: 46.994: [GC (Allocation Failure)  739724K->41964K(2096640K), 0.0009959 secs]\n" +
            "2017-05-07T13:59:22.588+0800: 47.229: [GC (Allocation Failure)  739820K->41964K(2096640K), 0.0009984 secs]\n" +
            "2017-05-07T13:59:22.829+0800: 47.470: [GC (Allocation Failure)  739820K->41932K(2096640K), 0.0012153 secs]\n" +
            "2017-05-07T13:59:23.156+0800: 47.797: [GC (Allocation Failure)  739788K->41932K(2096640K), 0.0027666 secs]\n" +
            "2017-05-07T13:59:23.401+0800: 48.042: [GC (Allocation Failure)  739788K->41956K(2096640K), 0.0010303 secs]\n" +
            "2017-05-07T13:59:23.640+0800: 48.281: [GC (Allocation Failure)  739812K->41988K(2096640K), 0.0010993 secs]\n" +
            "2017-05-07T13:59:23.876+0800: 48.517: [GC (Allocation Failure)  739844K->41956K(2096640K), 0.0011696 secs]\n" +
            "2017-05-07T13:59:24.204+0800: 48.845: [GC (Allocation Failure)  739812K->41988K(2096640K), 0.0014639 secs]\n" +
            "2017-05-07T13:59:24.475+0800: 49.116: [GC (Allocation Failure)  739844K->41900K(2096640K), 0.0055334 secs]\n" +
            "2017-05-07T13:59:24.787+0800: 49.428: [GC (Allocation Failure)  739756K->41932K(2096640K), 0.0014883 secs]\n" +
            "2017-05-07T13:59:25.095+0800: 49.737: [GC (Allocation Failure)  739788K->42004K(2096640K), 0.0011277 secs]\n" +
            "2017-05-07T13:59:25.373+0800: 50.014: [GC (Allocation Failure)  739860K->41956K(2096640K), 0.0018434 secs]\n" +
            "2017-05-07T13:59:25.668+0800: 50.309: [GC (Allocation Failure)  739812K->41980K(2096640K), 0.0017002 secs]\n" +
            "2017-05-07T13:59:26.060+0800: 50.702: [GC (Allocation Failure)  739836K->41980K(2096640K), 0.0018705 secs]\n" +
            "2017-05-07T13:59:26.431+0800: 51.072: [GC (Allocation Failure)  739836K->41956K(2096640K), 0.0029408 secs]\n" +
            "2017-05-07T13:59:26.712+0800: 51.353: [GC (Allocation Failure)  739812K->41964K(2096640K), 0.0018056 secs]\n" +
            "2017-05-07T13:59:26.989+0800: 51.630: [GC (Allocation Failure)  739820K->42004K(2096640K), 0.0029971 secs]\n" +
            "2017-05-07T13:59:27.276+0800: 51.917: [GC (Allocation Failure)  739860K->42100K(2096640K), 0.0011235 secs]\n" +
            "2017-05-07T13:59:27.508+0800: 52.149: [GC (Allocation Failure)  739956K->42100K(2096640K), 0.0015241 secs]\n" +
            "2017-05-07T13:59:27.740+0800: 52.381: [GC (Allocation Failure)  739956K->42100K(2096640K), 0.0010360 secs]\n" +
            "2017-05-07T13:59:28.026+0800: 52.667: [GC (Allocation Failure)  739956K->42036K(2096640K), 0.0020026 secs]\n" +
            "2017-05-07T13:59:28.296+0800: 52.938: [GC (Allocation Failure)  739892K->42036K(2096640K), 0.0014834 secs]\n" +
            "2017-05-07T13:59:28.531+0800: 53.172: [GC (Allocation Failure)  739892K->42132K(2096640K), 0.0010200 secs]\n" +
            "2017-05-07T13:59:28.766+0800: 53.407: [GC (Allocation Failure)  739988K->42036K(2096640K), 0.0017817 secs]\n";





    public static void test2(int ts) throws InterruptedException {
        final Set<String> set = Collections.synchronizedSet(new HashSet<String>());
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(ts);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("synchronizedSet");
                    for (int k = 0; k < 100; k++) {
                        set.add(Thread.currentThread().getName()
                                + "[" + k + "]" + "{" + json+ "}");
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.MINUTES);
        System.out.println(System.currentTimeMillis()-start);
    }




    public static void test3(int ts) throws InterruptedException {
        final Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(ts);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("ConcurrentHashMap");
                    for (int k = 0; k < 100; k++) {
                        set.add(Thread.currentThread().getName()
                                + "[" + k + "]" + "{" + json+ "}");
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.MINUTES);
        System.out.println(System.currentTimeMillis()-start);
    }

    public static void main(String[] args) throws InterruptedException {
        int ts=8;
        for (int i = 0; i < 10; i++) {
            test2(ts);

        }
        Thread.sleep(1000);
        System.out.println("-----");
        for (int i = 0; i < 10; i++) {
            test3(ts);

        }
        System.out.println("-----");
    }
}
