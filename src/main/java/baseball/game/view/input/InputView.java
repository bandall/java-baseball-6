package baseball.game.view.input;

import baseball.game.view.exception.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 유저의 입력을 받는 클래스
 */
public class InputView {

    private final Scanner scanner;

    public InputView(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public List<Integer> getGuessNumbers() {
        System.out.print("숫자를 입력해주세요 : ");
        String rawInput = scanner.nextLine();

        return convertOrThrow(rawInput);
    }

    public boolean askForGameContinue() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String rawInput = scanner.nextLine();

        if ("1".equals(rawInput)) {
            return true;
        }

        if ("2".equals(rawInput)) {
            return false;
        }

        throw new NotMenuOptionException();
    }

    private List<Integer> convertOrThrow(String rawInput) {
        List<String> stringList = List.of(rawInput.split(""));
        List<Integer> numberList = new ArrayList<>();

        try {
            for (String s : stringList) {
                numberList.add(Integer.valueOf(s));
            }
        } catch (NumberFormatException e) {
            throw new CharacterNotNumberException();
        }

        checkNumberList(numberList);

        return numberList;
    }

    private void checkNumberList(List<Integer> numberList) {
        if (numberList.size() != 3) {
            throw new SizeNotMatchException();
        }

        if (containsZero(numberList)) {
            throw new NumberContainsZeroException();
        }

        if (isDuplicated(numberList)) {
            throw new DuplicatedNumberException();
        }
    }


    private boolean containsZero(List<Integer> numberList) {
        return numberList.contains(0);
    }

    private boolean isDuplicated(List<Integer> numberList) {
        Set<Integer> numberSet = new HashSet<>(numberList);

        return numberSet.size() != numberList.size();
    }
}
