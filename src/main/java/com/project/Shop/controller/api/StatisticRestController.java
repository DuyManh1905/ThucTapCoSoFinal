package com.project.Shop.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Shop.dto.Statistic.BestSellerProduct;
import com.project.Shop.dto.Statistic.DayInMonthStatistic;
import com.project.Shop.dto.Statistic.DayInMonthStatistic2;
import com.project.Shop.dto.Statistic.MonthInYearStatistic;
import com.project.Shop.dto.Statistic.MonthInYearStatistic2;
import com.project.Shop.dto.Statistic.OrderStatistic;
import com.project.Shop.dto.Statistic.ProductStatistic;
import com.project.Shop.dto.Statistic.UserStatistic;
import com.project.Shop.service.AccountService;
import com.project.Shop.service.StatisticService;

@RestController
public class StatisticRestController {

    private final StatisticService statisticService;

    private final AccountService accountService;

    public StatisticRestController(StatisticService statisticService, AccountService accountService) {
        this.statisticService = statisticService;
        this.accountService = accountService;    }

    
    
    
    //====================================Doanh thu=====================================
    @GetMapping("/api/get-statistic-revenue-day-in-month")
    private List<DayInMonthStatistic> getDayInMonthStatistic(@RequestParam String month, @RequestParam String year) {
        return statisticService.getDayInMonthStatistic(month, year);
    }

    @GetMapping("/api/get-statistic-revenue-day-from-time")
    private List<DayInMonthStatistic2> getDayInMonthStatistic2(@RequestParam String fromDate, @RequestParam String toDate) {
        return statisticService.getDailyRevenue2(fromDate, toDate);
    }

    @GetMapping("/api/get-statistic-revenue-month-from-time")
    private List<MonthInYearStatistic2> getMonthlyStatistic(@RequestParam String fromMonth, @RequestParam String toMonth) {
        return statisticService.getMonthlyRevenue(fromMonth, toMonth);
    }

    @GetMapping("/api/get-statistic-revenue-month-in-year")
    private List<MonthInYearStatistic> getMonthInYearStatistic(@RequestParam String year) {
        return statisticService.getMonthInYearStatistic(year);
    }

    
    
  //====================================San pham=====================================
    @GetMapping("/api/get-bestseller-product")
    private List<BestSellerProduct> getBestSellerProductInTime(@RequestParam String fromDate, @RequestParam String toDate) {
        return statisticService.getBestSellerProduct(fromDate, toDate);
    }

    @GetMapping("/api/get-bestseller-product-all")
    private List<BestSellerProduct> getBestSellerProductAll() {
        return statisticService.getBestSellerProductAll();
    }

    @GetMapping("/api/get-bestseller-product-time")
    private List<BestSellerProduct> getBestSellerProductTime(@RequestParam String fromDate, @RequestParam String toDate) {
    	System.out.println("dong 57 statistic");
    	List<BestSellerProduct> x = statisticService.getBestSellerProduct(fromDate, toDate);
    	System.out.println(x.size());
    	return x;
    }

    @GetMapping("/get-statistic-user-by-month")
    public List<UserStatistic> getStatisticUserByMonth() {
        List<UserStatistic> userStatistics = accountService.getUserStatistics("2024-01-01", "2024-12-31");
        return  userStatistics;
    }

    @GetMapping("/api/get-statistic-product-time")
    public List<ProductStatistic> getStatisticProductInTime(@RequestParam String fromDate, @RequestParam String toDate) {
        List<ProductStatistic> productStatistics = statisticService.getStatisticProductInTime(fromDate, toDate);
        return  productStatistics;
    }

    @GetMapping("/api/get-statistic-order")
    public List<OrderStatistic> getStatisticOrder() {
        List<OrderStatistic> orderStatisticList = statisticService.getStatisticOrder();
        return orderStatisticList;
    }
}
