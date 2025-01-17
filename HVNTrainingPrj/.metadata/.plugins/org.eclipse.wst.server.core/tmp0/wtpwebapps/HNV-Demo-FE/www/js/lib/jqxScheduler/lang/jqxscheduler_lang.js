var jqxSchedulerLang 	= [];

jqxSchedulerLang['vn'] 	= {
        "/": "/",
        ":": ":",
        firstDay: 0,
        days: {
			names: ["Chủ Nhật","Thứ Hai","Thứ Ba","Thứ Tư","Thứ Năm","Thứ Sáu","Thứ Bảy"],
			namesAbbr: ["CN","Hai","Ba","Tư","Năm","Sáu","Bảy"],
			namesShort: ["C","H","B","T","N","S","B"]
		},
		months: {
			names: ["Tháng Giêng","Tháng Hai","Tháng Ba","Tháng Tư","Tháng Năm","Tháng Sáu","Tháng Bảy","Tháng Tám","Tháng Chín","Tháng Mười","Tháng Mười Một","Tháng Mười Hai",""],
			namesAbbr: ["Thg1","Thg2","Thg3","Thg4","Thg5","Thg6","Thg7","Thg8","Thg9","Thg10","Thg11","Thg12",""]
		},
		AM: ["SA","sa","SA"],
		PM: ["CH","ch","CH"],
        eras: [{
            name: "A.D.",
            start: null,
            offset: 0
        }],
        twoDigitYearMax: 2029,
        patterns: {
            d: "M/d/yyyy",
            D: "dddd, MMMM dd, yyyy",
            t: "h:mm tt",
            T: "h:mm:ss tt",
            f: "dddd, MMMM dd, yyyy h:mm tt",
            F: "dddd, MMMM dd, yyyy h:mm:ss tt",
            M: "MMMM dd",
            Y: "yyyy MMMM",
            S: "yyyy\u0027-\u0027MM\u0027-\u0027dd\u0027T\u0027HH\u0027:\u0027mm\u0027:\u0027ss",
            ISO: "yyyy-MM-dd hh:mm:ss",
            ISO2: "yyyy-MM-dd HH:mm:ss",
            d1: "dd.MM.yyyy",
            d2: "dd-MM-yyyy",
            d3: "dd-MMMM-yyyy",
            d4: "dd-MM-yy",
            d5: "H:mm",
            d6: "HH:mm",
            d7: "HH:mm tt",
            d8: "dd/MMMM/yyyy",
            d9: "MMMM-dd",
            d10: "MM-dd",
            d11: "MM-dd-yyyy"
        },
        agendaDateColumn: "Ngày",
        agendaTimeColumn: "Giờ",
        agendaAppointmentColumn: "Lịch hẹn",
        backString: "Trở lại",
        forwardString: "Chuyển tiếp",
        toolBarPreviousButtonString: "Trước đó",
        toolBarNextButtonString: "Tiếp theo",
        emptyDataString: "Không có dữ liệu",
        loadString: "Đang nạp...",
        clearString: "Xóa",
        todayString: "Hôm nay",
        dayViewString: "Ngày",
        weekViewString: "Tuần",
        monthViewString: "Tháng",
        agendaViewString: "Lịch",
        timelineDayViewString: "Timeline Day",
        timelineWeekViewString: "Timeline Week",
        timelineMonthViewString: "Timeline Month",
        agendaAllDayString: "Tất cả các ngày",
        loadingErrorMessage: "The data is still loading and you cannot set a property or call a method. You can do that once the data binding is completed. jqxScheduler raises the 'bindingComplete' event when the binding is completed.",
        editRecurringAppointmentDialogTitleString: "Thay đổi lịch hẹn",
        editRecurringAppointmentDialogContentString: "Do you want to edit only this occurrence or the series?",
        editRecurringAppointmentDialogOccurrenceString: "Thay đổi",
        editRecurringAppointmentDialogSeriesString: "Edit The Series",
        editDialogTitleString: "Thay đổi lịch hẹn",
        editDialogCreateTitleString: "Tạo lịch hẹn",
        contextMenuEditAppointmentString: "Thay đổi lịch hẹn",
        contextMenuCreateAppointmentString: "Tạo lịch hẹn",
        editDialogSubjectString: "Mục đích",
        editDialogLocationString: "Địa điểm",
        editDialogFromString: "Từ",
        editDialogToString: "Đến",
        editDialogAllDayString: "All day",
        editDialogExceptionsString: "Exceptions",
        editDialogResetExceptionsString: "Reset on Save",
        editDialogDescriptionString: "Miêu tả",
        editDialogResourceIdString: "Chủ nhân",
        editDialogStatusString: "Tình trạng",
        editDialogColorString: "Màu sắc",
        editDialogColorPlaceHolderString: "Hãy chọn",
        editDialogTimeZoneString: "Time Zone",
        editDialogSelectTimeZoneString: "Select Time Zone",
        editDialogSaveString: "Lưu",
        editDialogDeleteString: "Xóa",
        editDialogCancelString: "Bỏ qua",
        editDialogRepeatString: "Lặp lại",
        editDialogRepeatEveryString: "Repeat every",
        editDialogRepeatEveryWeekString: "tuần",
        editDialogRepeatEveryMonthString: "tháng",
        editDialogRepeatEveryYearString: "năm",
        editDialogRepeatEveryDayString: "ngày",
        editDialogRepeatNeverString: "Không bao giờ",
        editDialogRepeatDailyString: "Hàng ngày",
        editDialogRepeatWeeklyString: "Hàng tuần",
        editDialogRepeatMonthlyString: "Hàng tháng",
        editDialogRepeatYearlyString: "Hàng năm",                    
        editDialogRepeatEveryMonthDayString: "Ngày",
        editDialogRepeatFirstString: "first",
        editDialogRepeatSecondString: "second",
        editDialogRepeatThirdString: "third",
        editDialogRepeatFourthString: "fourth",
        editDialogRepeatLastString: "last",
        editDialogRepeatEndString: "End",
        editDialogRepeatAfterString: "After",
        editDialogRepeatOnString: "On",
        editDialogRepeatOfString: "of",
        editDialogRepeatOccurrencesString: "occurrence(s)",
        editDialogRepeatSaveString: "Save Occurrence",
        editDialogRepeatSaveSeriesString: "Save Series",
        editDialogRepeatDeleteString: "Delete Occurrence",
        editDialogRepeatDeleteSeriesString: "Delete Series",
        editDialogStatuses: {
            free		: "Rảnh",
            tentative	: "Có ý định",
            busy		: "Bận",
            outOfOffice	: "Không có mặt"
        }
    }
jqxSchedulerLang['en'] 	= {
        "/": "/",
        ":": ":",
        firstDay: 0,
        days: {
			names: ["dimanche","lundi","mardi","mercredi","jeudi","vendredi","samedi"],
			namesAbbr: ["dim.","lun.","mar.","mer.","jeu.","ven.","sam."],
			namesShort: ["di","lu","ma","me","je","ve","sa"]
		},
		months: {
			names: ["janvier","février","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","décembre",""],
			namesAbbr: ["janv.","févr.","mars","avr.","mai","juin","juil.","août","sept.","oct.","nov.","déc.",""]
		},
		AM: null,
		PM: null,
		eras: [{"name":"ap. J.-C.","start":null,"offset":0}],
        twoDigitYearMax: 2029,
        patterns: {
            d: "M/d/yyyy",
            D: "dddd, MMMM dd, yyyy",
            t: "h:mm tt",
            T: "h:mm:ss tt",
            f: "dddd, MMMM dd, yyyy h:mm tt",
            F: "dddd, MMMM dd, yyyy h:mm:ss tt",
            M: "MMMM dd",
            Y: "yyyy MMMM",
            S: "yyyy\u0027-\u0027MM\u0027-\u0027dd\u0027T\u0027HH\u0027:\u0027mm\u0027:\u0027ss",
            ISO: "yyyy-MM-dd hh:mm:ss",
            ISO2: "yyyy-MM-dd HH:mm:ss",
            d1: "dd.MM.yyyy",
            d2: "dd-MM-yyyy",
            d3: "dd-MMMM-yyyy",
            d4: "dd-MM-yy",
            d5: "H:mm",
            d6: "HH:mm",
            d7: "HH:mm tt",
            d8: "dd/MMMM/yyyy",
            d9: "MMMM-dd",
            d10: "MM-dd",
            d11: "MM-dd-yyyy"
        },
        agendaDateColumn: "Ngày",
        agendaTimeColumn: "Giờ",
        agendaAppointmentColumn: "Điểm hẹn",
        backString: "Trở lại",
        forwardString: "Forward",
        toolBarPreviousButtonString: "previous",
        toolBarNextButtonString: "next",
        emptyDataString: "No data to display",
        loadString: "Loading...",
        clearString: "Clear",
        todayString: "Today",
        dayViewString: "Day",
        weekViewString: "Week",
        monthViewString: "Month",
        agendaViewString: "Agenda",
        timelineDayViewString: "Timeline Day",
        timelineWeekViewString: "Timeline Week",
        timelineMonthViewString: "Timeline Month",
        agendaAllDayString: "all day",
        loadingErrorMessage: "The data is still loading and you cannot set a property or call a method. You can do that once the data binding is completed. jqxScheduler raises the 'bindingComplete' event when the binding is completed.",
        editRecurringAppointmentDialogTitleString: "Edit Recurring Appointment",
        editRecurringAppointmentDialogContentString: "Do you want to edit only this occurrence or the series?",
        editRecurringAppointmentDialogOccurrenceString: "Edit Occurrence",
        editRecurringAppointmentDialogSeriesString: "Edit The Series",
        editDialogTitleString: "Edit Appointment",
        editDialogCreateTitleString: "Create New Appointment",
        contextMenuEditAppointmentString: "Edit Appointment",
        contextMenuCreateAppointmentString: "Create New Appointment",
        editDialogSubjectString: "Subject",
        editDialogLocationString: "Location",
        editDialogFromString: "From",
        editDialogToString: "To",
        editDialogAllDayString: "All day",
        editDialogExceptionsString: "Exceptions",
        editDialogResetExceptionsString: "Reset on Save",
        editDialogDescriptionString: "Description",
        editDialogResourceIdString: "Owner",
        editDialogStatusString: "Status",
        editDialogColorString: "Color",
        editDialogColorPlaceHolderString: "Select Color",
        editDialogTimeZoneString: "Time Zone",
        editDialogSelectTimeZoneString: "Select Time Zone",
        editDialogSaveString: "Save",
        editDialogDeleteString: "Delete",
        editDialogCancelString: "Cancel",
        editDialogRepeatString: "Repeat",
        editDialogRepeatEveryString: "Repeat every",
        editDialogRepeatEveryWeekString: "week(s)",
        editDialogRepeatEveryYearString: "year(s)",
        editDialogRepeatEveryDayString: "day(s)",
        editDialogRepeatNeverString: "Never",
        editDialogRepeatDailyString: "Daily",
        editDialogRepeatWeeklyString: "Weekly",
        editDialogRepeatMonthlyString: "Monthly",
        editDialogRepeatYearlyString: "Yearly",
        editDialogRepeatEveryMonthString: "month(s)",
        editDialogRepeatEveryMonthDayString: "Day",
        editDialogRepeatFirstString: "first",
        editDialogRepeatSecondString: "second",
        editDialogRepeatThirdString: "third",
        editDialogRepeatFourthString: "fourth",
        editDialogRepeatLastString: "last",
        editDialogRepeatEndString: "End",
        editDialogRepeatAfterString: "After",
        editDialogRepeatOnString: "On",
        editDialogRepeatOfString: "of",
        editDialogRepeatOccurrencesString: "occurrence(s)",
        editDialogRepeatSaveString: "Save Occurrence",
        editDialogRepeatSaveSeriesString: "Save Series",
        editDialogRepeatDeleteString: "Delete Occurrence",
        editDialogRepeatDeleteSeriesString: "Delete Series",
        editDialogStatuses: {
            free: "Free",
            tentative: "Tentative",
            busy: "Busy",
            outOfOffice: "Out of Office"
        }
    }