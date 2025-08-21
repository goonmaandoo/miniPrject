package com.the.ex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.the.dao.BookingDao;
import com.the.dao.Customer2Dao;
import com.the.dao.MainDao;
import com.the.dao.MoviesDao;
import com.the.dto.BookingDto;
import com.the.dto.Customer2Dto;
import com.the.dto.MainDto;
import com.the.dto.MainDto2;
import com.the.dto.MoviesDto;
import com.the.util.UserInput;

public class MainEx {
	public static MainDao mDao = new MainDao();

	public static void main(String[] args) {

		boolean play = true;

		while (play) {
			System.out.println("=======영화 예매 사이트=======");
			System.out.println("1. 영화 예매하기");
			System.out.println("2. 영화 예매 조회");
			System.out.println("3. 영화 예매 취소");
			System.out.println("4. 영화 예매 수정");
			System.out.println("5. 관리자 모드");
			System.out.println("0. 예매 종료");
			System.out.println("=======================");

			switch (UserInput.inputInt("메뉴입력 :")) {
			case 1:
				System.out.println("=======회원정보 입력=======");
				boolean login = true;
				while (login) {
					String input = UserInput.inputString("회원이십니까? Y or N");
					if (input.equals("N")) {
						insertMainCustomer();
						login = false;
					} else if (input.equals("Y")) {
						login = false;
					} else {
						System.out.println("잘못 입력하셨습니다");
					}
				}
				selectMainMovie();
				System.out.println("영화 예매하기");
				insertMainBooking();
				// System.out.println("영화가 예매되었습니다.");
				break;
			case 2:
				selectMain2();
				break;
			case 3:
				deleteMain();
				break;
			case 4:
				updateMain();
				break;
			case 5:
				ownerMode();
				break;
			case 0:
				play = false;
				System.out.println("프로그램 종료");
				break;
			default:
				System.out.println("잘못된 입력");
				break;
			}

		}

	}

	// select문
	// 관리자모드-전체 내역 조회
	private static void selectMain() {
		System.out.println("=====전체 목록 조회=====");
		System.out.println("예매 내역 조회");
		ArrayList<MainDto> resultDto = mDao.selectAll();
		for (MainDto item : resultDto) {
			System.out.println(item);
		}
	}

	// 해당Id 예매 내역 조회
	private static void selectMain2() {
		System.out.println("=====예매 목록=====");
		while (true) {
			long customerId;
			while (true) {
				customerId = UserInput.inputLong("고객ID");
				Customer2Dto cDto = Customer2Dao.findbyId(customerId);
				if (cDto == null) {
					System.out.println("해당 ID가 없습니다.");
				} else {
					break;
				}
			}
			ArrayList<MainDto2> resultDto = mDao.selectAll2(customerId);
			boolean idExists = false;
			for (MainDto2 item : resultDto) {
				if (item.getCustomerId() == customerId) {
					System.out.println(item);
					idExists = true;
				}
			}
			if (!idExists) {
				System.out.println("해당 ID로 예매된 내역이 없습니다. 다시 입력하세요.");
			} else {
				break;
			}
		}
	}

	// 고객 내역 select문
	private static void selectMainCustomer() {
		System.out.println("=====회원 조회=====");
		ArrayList<Customer2Dto> resultDto = Customer2Dao.selectAll();
		for (Customer2Dto item : resultDto) {
			System.out.println(item);
		}
	}

	// 영화 내역 select문
	private static void selectMainMovie() {
		System.out.println("=====영화 목록 조회=====");
		ArrayList<MoviesDto> resultDto = MoviesDao.selectAll();
		for (MoviesDto item : resultDto) {
			System.out.println(item);
		}

	}

	// insert문
	// 고객 추가
	private static void insertMainCustomer() {

		System.out.println("=====고객 추가=====");
		long customerId = UserInput.inputLong("고객ID");
		String name = UserInput.inputString("고객이름");
		String phone = UserInput.inputString("핸드폰 번호(000-000-0000 형식)");
		boolean phnum = true;
		while (phnum) {
			if (mDao.isValidPhoneNumber(phone)) {
				phnum = false;
			} else {
				System.out.println("잘못된 번호 형식입니다.");
				phone = UserInput.inputString("핸드폰 번호(000-000-0000 형식)");
			}
		}
		Customer2Dto dto = new Customer2Dto(customerId, name, phone);
		Customer2Dao.insert(dto);
	}

	// 영화 예매 추가
	private static void insertMainBooking() {
		System.out.println("=====영화 예매=====");
		long customerId;
		while (true) {
			customerId = UserInput.inputLong("고객ID");
			Customer2Dto cDto = Customer2Dao.findbyId(customerId);
			if (cDto == null) {
				System.out.println("해당 ID가 없습니다.");
			} else {
				break;
			}
		}
		long movieId;
		while (true) {
			movieId = UserInput.inputLong("영화번호");
			MoviesDto mDto = MoviesDao.findbyId(movieId);
			if (mDto == null) {
				System.out.println("해당 영화가 없습니다.");
			} else {
				break;
			}
		}
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime showtime = UserInput.inputLocalDateTime("상영시간");
		boolean time = true;
		while (time) {
			if (showtime.isBefore(now)) {
				System.out.println("상영시간을 잘못 입력하셨습니다");
				showtime = UserInput.inputLocalDateTime("상영시간");
			} else {
				time = false;
			}
		}
		String seatsBooked = UserInput.inputString("예매좌석");
		BookingDto dto = new BookingDto(customerId, movieId, showtime, seatsBooked);
		BookingDao.insert(dto);
		System.out.println("영화 예매가 완료되었습니다.");
	}

	// 영화 추가
	private static void insertMainMovie() {
		System.out.println("=====영화 추가=====");
		String movieName = UserInput.inputString("영화제목");
		String genre = UserInput.inputString("장르");
		LocalDate releaseDate = UserInput.inputDate("개봉일");
		MoviesDto dto = new MoviesDto(movieName, genre, releaseDate);
		MoviesDao.insert(dto);
	}

	// update
	// 관리자 update
	private static void updateMainMovie() {
		selectMainMovie();
		System.out.println("=====영화 수정=====");
		long movieId = UserInput.inputLong("수정할 영화번호");

		MoviesDto mDto = MoviesDao.findbyId(movieId);
		if (mDto == null) {
			System.out.println("해당 영화 ID가 없습니다.");
			return;
		}
		String movieName = UserInput.inputString("수정할 영화이름", mDto.getMovieName());
		String genre = UserInput.inputString("수정할 영화장르", mDto.getGenre());
		LocalDate releaseDate = UserInput.inputDate("수정할 개봉일", mDto.getReleaseDate());
		if (releaseDate == null) {
			releaseDate = mDto.getReleaseDate();
		}
		MoviesDao.update(movieId, movieName, genre, releaseDate);
		System.out.println("수정 완료");
	}

	// 예매권 수정
	private static void updateMain() {
		System.out.println("=====예매권 수정=====");
		long customerId;
		while (true) {
			customerId = UserInput.inputLong("고객ID ");
			String correct = MainDao.getPhoneLast4(customerId);
			if (correct == null) {
				System.out.println("고객ID가 존재하지 않습니다.");
				return; // 종료
			}
			String password = UserInput.inputString("핸드폰 뒷자리");
			if (password.equals(correct)) {
				System.out.println("본인 확인 완료");
				break;
			} else {
				System.out.println("본인이 아닙니다. 다시 입력하세요");
			}
		}
		System.out.println("=====예매 목록=====");
		while (true) {
			ArrayList<MainDto2> resultDto = mDao.selectAll2(customerId);
			boolean idExists = false;
			for (MainDto2 item : resultDto) {
				if (item.getCustomerId() == customerId) {
					System.out.println(item);
					idExists = true;
				}
			}
			if (!idExists) {
				System.out.println("해당 ID로 예매된 내역이 없습니다.");
				return;
			} else {
				break;
			}
		}

		long bookingId = UserInput.inputLong("예매번호");
		BookingDto bDto = BookingDao.findById(bookingId);
		if (bDto == null) {
			System.out.println("해당 예매번호가 없습니다.");
			return;
		}
		LocalDateTime showTime = UserInput.inputLocalDateTime("수정할 상영시간", bDto.getShowTime());
		LocalDateTime now = LocalDateTime.now();
		boolean time = true;
		while (time) {
			if (showTime.isBefore(now)) {
				System.out.println("상영시간을 잘못 입력하셨습니다");
				showTime = UserInput.inputLocalDateTime("상영시간");
			} else {
				time = false;
			}
		}
		String seatsBooked = UserInput.inputString("수정할 예매좌석", bDto.getSeatsBooked());
		BookingDao.update(bookingId, showTime, seatsBooked);
		System.out.println("수정 완료되었습니다. ");
	}

	private static void deleteMain() {
		System.out.println("=====예매 삭제=====");
		long customerId;
		while (true) {
			customerId = UserInput.inputLong("고객ID ");
			String correct = MainDao.getPhoneLast4(customerId);
			if (correct == null) {
				System.out.println("고객ID가 존재하지 않습니다.");
				return; // 종료
			}
			String password = UserInput.inputString("핸드폰 뒷자리");
			if (password.equals(correct)) {
				System.out.println("본인 확인 완료");
				break;
			} else {
				System.out.println("본인이 아닙니다. 다시 입력하세요");
			}
		}
		System.out.println("=====예매 목록=====");
		while (true) {
			ArrayList<MainDto2> resultDto = mDao.selectAll2(customerId);
			boolean idExists = false;
			for (MainDto2 item : resultDto) {
				if (item.getCustomerId() == customerId) {
					System.out.println(item);
					idExists = true;
				}
			}
			if (!idExists) {
				System.out.println("해당 ID로 예매된 내역이 없습니다.");
				return;
			} else {
				break;
			}
		}
		System.out.println("삭제할 영화 예매번호");
		long bookingId = UserInput.inputLong("예매번호");
		BookingDto bDto = BookingDao.findById(bookingId);
		if (bDto == null) {
			System.out.println("해당 예매번호가 없습니다.");
			return;
		}
		BookingDao.delete(bookingId);
	}

	private static void ownerMode() {
		int password = UserInput.inputInt("비밀번호를 입력해주세요");
		boolean owner = true;
		if (password == 1234) {
			while (owner) {
				System.out.println("===관리자 메뉴===");
				System.out.println("1. 영화 수정");
				System.out.println("2. 영화 추가");
				System.out.println("3. 예매자 전체출력");
				System.out.println("4. 회원 명단");
				System.out.println("5. 영화 전체 목록");
				System.out.println("0. 관리자 모드 나가기");
				switch (UserInput.inputInt("메뉴선택 :")) {
				case 1:
					updateMainMovie();
					break;
				case 2:
					insertMainMovie();
					break;
				case 3:
					selectMain();
					break;
				case 4:
					selectMainCustomer();
					break;
				case 5:
					selectMainMovie();
					break;
				case 0:
					owner = false;
					System.out.println("관리자 모드가 종료됩니다.");
					break;
				default:
					System.out.println("잘못된 입력");
					break;
				}
			}
		} else {
			System.out.println("비밀번호를 잘못입력하셨습니다.");
			System.out.println("관리자가 아닙니다.");

		}
	}

}
