package baseball.game.model;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.game.model.dto.RoundResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameModelTest {

    @Test
    @DisplayName("랜덤넘버_생성_테스트")
    public void generateNumberList() {
        GameModel gameModel = new GameModel();

        List<Integer> randomAnswer = gameModel.generateRandomAnswer();
        assertThat(randomAnswer.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("결과계산_로직_테스트_스트라이크")
    public void calculateRoundResult() {
        GameModel gameModel = new GameModel();
        List<Integer> randomAnswer = List.of(1, 2, 3);
        List<Integer> userGuessNumber = List.of(1, 2, 3);

        RoundResult roundResult = gameModel.calculateRoundResult(randomAnswer, userGuessNumber);

        assertThat(roundResult.getBallCount()).isEqualTo(0);
        assertThat(roundResult.getStrikeCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("결과계산_로직_테스트_볼")
    public void calculateRoundResult2() {
        GameModel gameModel = new GameModel();
        List<Integer> randomAnswer = List.of(1, 2, 3);
        List<Integer> userGuessNumber = List.of(3, 1, 2);

        RoundResult roundResult = gameModel.calculateRoundResult(randomAnswer, userGuessNumber);

        assertThat(roundResult.getBallCount()).isEqualTo(3);
        assertThat(roundResult.getStrikeCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("결과계산_로직_테스트_낫싱")
    public void calculateRoundResult3() {
        GameModel gameModel = new GameModel();
        List<Integer> randomAnswer = List.of(1, 2, 3);
        List<Integer> userGuessNumber = List.of(4, 5, 6);

        RoundResult roundResult = gameModel.calculateRoundResult(randomAnswer, userGuessNumber);

        assertThat(roundResult.getBallCount()).isEqualTo(0);
        assertThat(roundResult.getStrikeCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("결과계산_로직_테스트_볼_스트라이크")
    public void calculateRoundResult4() {
        GameModel gameModel = new GameModel();
        List<Integer> randomAnswer = List.of(1, 2, 3);
        List<Integer> userGuessNumber = List.of(1, 3, 5);

        RoundResult roundResult = gameModel.calculateRoundResult(randomAnswer, userGuessNumber);

        assertThat(roundResult.getBallCount()).isEqualTo(1);
        assertThat(roundResult.getStrikeCount()).isEqualTo(1);
    }
}