package convertor;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.Numbers;

public class LottoNumberConvertorTest {

	private static final String winningNumbers = "1, 2, 3, 4, 5, 6";
	private static final String bonusNumber = "7";

	@Test
	void 당첨_번호를_입력받아_정상_변환한다() {
		Numbers numbers = LottoNumberConvertor.convertWinningNumber(winningNumbers);

		List<Integer> winningNumbers = numbers.getNumbers();
		List<Integer> expectedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

		assertThat(winningNumbers).isEqualTo(expectedNumbers);
	}

	@Test
	void 당첨_번호는_숫자여야_한다() {
		assertThatThrownBy(() -> LottoNumberConvertor.convertWinningNumber("a"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 당첨 번호는 숫자여야 합니다. 다시 입력해주세요");
	}

	@ParameterizedTest(name = "{index} : 당첨 번호 {0} 입력")
	@ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
	void 당첨_번호는_여섯개여야_한다(String input) {
		assertThatThrownBy(() -> LottoNumberConvertor.convertWinningNumber(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 당첨 번호는 여섯개여야 합니다.");
	}

	@Test
	void 보너스_번호를_입력받아_정상_변환한다() {
		Integer bonusNumber = LottoNumberConvertor.convertBonusNumber(bonusNumber);

		Integer expectedNumber = 7;

		assertThat(bonusNumber).isEqualTo(expectedNumber);
	}

}