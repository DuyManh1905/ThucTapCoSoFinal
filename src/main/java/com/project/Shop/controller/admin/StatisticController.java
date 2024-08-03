package com.project.Shop.controller.admin;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Shop.repository.BillRepository;
import com.project.Shop.service.AccountService;
import com.project.Shop.service.StatisticService;

@Controller
public class StatisticController {

	private final BillRepository billRepository;

	private final StatisticService statisticService;

	public StatisticController(AccountService accountService, BillRepository billRepository,
			StatisticService statisticService) {
		this.billRepository = billRepository;
		this.statisticService = statisticService;
	}

	@GetMapping("/admin/thong-ke-doanh-thu")
	public String viewStatisticRevenuePage(Model model) {
		LocalDateTime currentDateTime = LocalDateTime.now();

		// ngay dau tien va ngay cuoi cung tuan hien tai
		LocalDateTime firstDayOfWeek = currentDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
				.with(LocalTime.MIN);
		LocalDateTime lastDayOfWeek = firstDayOfWeek.plusDays(6).with(LocalTime.MAX);

		// ngày đầu tiên và ngày cuối cùng của tháng hiện tại
		LocalDateTime firstDayOfMonth = currentDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
		LocalDateTime lastDayOfMonth = currentDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// thời gian bắt đầu của ngày hiện tại
		LocalDateTime startOfDay = currentDateTime.toLocalDate().atStartOfDay();
		String startOfDayStr = startOfDay.format(formatter);
		String firstDayOfWeekStr = firstDayOfWeek.format(formatter);
		String lastDayOfWeekStr = lastDayOfWeek.format(formatter);
		String firstDayOfMonthStr = firstDayOfMonth.format(formatter);
		String lastDayOfMonthStr = lastDayOfMonth.format(formatter);

		// doanh thu tuan/thang/ngay/all
		model.addAttribute("revenueAll", billRepository.calculateTotalRevenue());
		model.addAttribute("revenueWeek",
				billRepository.calculateTotalRevenueFromDate(firstDayOfWeekStr, lastDayOfWeekStr));
		model.addAttribute("revenueToday",
				billRepository.calculateTotalRevenueFromDate(startOfDayStr, currentDateTime.format(formatter)));
		model.addAttribute("revenueMonth",
				billRepository.calculateTotalRevenueFromDate(firstDayOfMonthStr, lastDayOfMonthStr));

		// doanh thu tuan truoc
		LocalDateTime lastWeekStart = currentDateTime.minusWeeks(1)
				.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
		LocalDateTime lastWeekEnd = lastWeekStart.plusDays(6).with(LocalTime.MAX);
		String lastWeekStartStr = lastWeekStart.format(formatter);
		String lastWeekEndStr = lastWeekEnd.format(formatter);

		// doanh thu thang truoc
		LocalDateTime lastMonthStart = currentDateTime.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth())
				.with(LocalTime.MIN);
		LocalDateTime lastMonthEnd = lastMonthStart.plusMonths(1).minusDays(1).with(LocalTime.MAX);
		String lastMonthStartStr = lastMonthStart.format(formatter);
		String lastMonthEndStr = lastMonthEnd.format(formatter);

		// Adjustments for the previous day
		LocalDateTime yesterdayStartOfDay = currentDateTime.minusDays(1).toLocalDate().atStartOfDay();
		String yesterdayStartOfDayStr = yesterdayStartOfDay.format(formatter);
		String yesterdayEndOfDayStr = currentDateTime.with(LocalTime.MIN).format(formatter);

		Double revenueYesterday = billRepository.calculateTotalRevenueFromDate(yesterdayStartOfDayStr,
				yesterdayEndOfDayStr);
		Double revenueLastWeek = billRepository.calculateTotalRevenueFromDate(lastWeekStartStr, lastWeekEndStr);
		Double revenueLastMonth = billRepository.calculateTotalRevenueFromDate(lastMonthStartStr, lastMonthEndStr);

		Double revenueToday = (Double) model.getAttribute("revenueToday");
		Double revenueWeek = (Double) model.getAttribute("revenueWeek");
		Double revenueMonth = (Double) model.getAttribute("revenueMonth");

		Double percentageYesterday = calculatePercentage(revenueYesterday, revenueToday);
		Double percentageLastWeek = calculatePercentage(revenueLastWeek, revenueWeek);
		Double percentageLastMonth = calculatePercentage(revenueLastMonth, revenueMonth);

		model.addAttribute("percentageYesterday", percentageYesterday);
		model.addAttribute("percentageLastWeek", percentageLastWeek);
		model.addAttribute("percentageLastMonth", percentageLastMonth);
		model.addAttribute("bestSellers", statisticService.getBestSellerProductAll());

		return "/admin/thong-ke-doanh-thu";
	}

	@GetMapping("/admin/thong-ke-san-pham")
	public String viewStatisticProductPage(Model model) {

		return "/admin/thong-ke-san-pham";
	}

	
	//tính toán tỷ lệ phần trăm thay đổi giữa hai giá trị 
	private double calculatePercentage(double baseValue, double comparedValue) {
		if (baseValue == 0 && comparedValue > 0) {
			return 99;
		}
		if (baseValue == 0) {
			return 0; // Tránh chia cho 0
		}
		return ((comparedValue - baseValue) / baseValue) * 100;
	}
}
