import java.util.*;

/*
★入力例★　実行して、コンソールウインドウから以下のように入力して動作確認しましょう。（行末でEnterキー）
3 13
3 13
2 13
3 12
3 12
★出力例★　上記のテストデータでは、以下の結果が出たら正解です。
SA H6 H10 HA CA
スリーカード
*/


public class Kadai2_1 {
    static String[] SUIT_STRING = {"S", "C", "D", "H"};
    static String[] NUMBER_STRING = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    private int[] suit = new int[5];
    private int[] number = new int[5];

    private int[] suitCount = { 0, 0, 0, 0 };
    private int[] numberCount = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0};


    public static void main(String[] args) {
        new Kadai2_1();
    }

    public Kadai2_1() {
        cardYomu();        // カード情報をコンソールから読み、
        cardStringKaku();  // カードの絵柄と番号を書いて、
        hanteiJunbi();     // 役判定のために準備して、
        yakuhantei();      // 役を判定する。
    }

    private void cardYomu() {
        try {
            Scanner sc = new Scanner(System.in); // コンソールを入力元として指定
            for (int i = 0; i < 5; ++i) {  // 5枚のカードを読むよ
                suit[i] = sc.nextInt();   // スート（スペードが0、クラブが1、…）を読んで代入
                number[i] = sc.nextInt(); // 番号（01～13）を読んで代入
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void hanteiJunbi() {
        for (int i = 0; i < 5; ++i) {
            suitCount[ suit[i] ] += 1;
            numberCount[number[i]] += 1;
        }
    }

    private void cardStringKaku() {
        for (int i = 0; i < 4; ++i) {
            System.out.print(SUIT_STRING[suit[i]] + NUMBER_STRING[number[i] - 1] + " ");
        }
        System.out.println(SUIT_STRING[suit[4]] + NUMBER_STRING[number[4] - 1]);
    }

    private void yakuhantei() {
        // ロイヤル(ストレート)フラッシュ！！！
        if ((suit[0] == suit[1] && suit[0] == suit[2] && suit[0] == suit[3] && suit[0] == suit[4])
              && ((number[0] + number[1] + number[2] + number[3] + number[4]) == 47)
              && (number[0] == 1 || number[1] == 1 || number[2] == 1 || number[3] == 1 || number[4] == 1)) {
            System.out.println("ロイヤルストレートフラッシュ");
        }

        // ストレートフラッシュの判定
        else if((suit[0] == suit[1] && suit[0] == suit[2] && suit[0] == suit[3] && suit[0] == suit[4]) &&

                ((number[1] == number[0]+1 && number[2] == number[1]+1 && number[3] == number[2]+1 && number[4] == number[3]+1)
                || (number[0] == 13 && number[1] == 1 && number[2] == 2 && number[3] == 3 && number[4] == 4)
                || (number[1] == 13 && number[2] == 1 && number[3] == 2 && number[4] == 3 && number[0] == 4)
                || (number[2] == 13 && number[3] == 1 && number[4] == 2 && number[0] == 3 && number[1] == 4)
                || (number[3] == 13 && number[4] == 1 && number[0] == 2 && number[1] == 3 && number[2] == 4)
                || (number[4] == 13 && number[0] == 1 && number[1] == 2 && number[2] == 3 && number[3] == 4))
        )
        {
            System.out.println("ストレートフラッシュ");
        }
        // フォーカードの判定
        else if((number[0] == number[1] && number[0] == number[2] && number[0] == number[3] && number[0] != number[4])
                ||(number[0] == number[1] && number[0] == number[2] && number[0] == number[4] && number[0] != number[3])
                ||(number[0] == number[1] && number[0] == number[3] && number[0] == number[4] && number[0] != number[2])
                ||(number[0] == number[2] && number[0] == number[3] && number[0] == number[4] && number[0] != number[1])
                ||(number[1] == number[2] && number[1] == number[3] && number[1] == number[4] && number[1] != number[0])
        ){
            System.out.println("フォーカード");
        }
        // フルハウスの判定
        else if((numberCount[1] == 3 || numberCount[2] == 3 || numberCount[3] == 3
                || numberCount[4] == 3 || numberCount[5] == 3 || numberCount[6] == 3 || numberCount[7] == 3
                || numberCount[8] == 3 || numberCount[9] == 3 || numberCount[10] == 3 || numberCount[11] == 3
                || numberCount[12] == 3 || numberCount[13] == 3)
                &&
                (numberCount[1] == 2 || numberCount[2] == 2 || numberCount[3] == 2
                || numberCount[4] == 2 || numberCount[5] == 2 || numberCount[6] == 2 || numberCount[7] == 2
                || numberCount[8] == 2 || numberCount[9] == 2 || numberCount[10] == 2 || numberCount[11] == 2
                || numberCount[12] == 2 || numberCount[13] == 2))
        {
            System.out.println("フルハウス");
        }
        //フラッシュ
        else if(suitCount[0] == 5 || suitCount[1] == 5 || suitCount[2] == 5 || suitCount[3] == 5)
        {
            System.out.println("フラッシュ");
        }
        //ストレート
        else if((number[1] == number[0]+1 && number[2] == number[1]+1 && number[3] == number[2]+1 && number[4] == number[3]+1)
                || (number[0] == 13 && number[1] == 1 && number[2] == 2 && number[3] == 3 && number[4] == 4)
                || (number[1] == 13 && number[2] == 1 && number[3] == 2 && number[4] == 3 && number[0] == 4)
                || (number[2] == 13 && number[3] == 1 && number[4] == 2 && number[0] == 3 && number[1] == 4)
                || (number[3] == 13 && number[4] == 1 && number[0] == 2 && number[1] == 3 && number[2] == 4)
                || (number[4] == 13 && number[0] == 1 && number[1] == 2 && number[2] == 3 && number[3] == 4))
        {
            System.out.println("ストレート");
        }
        // スリーカード
        else if (numberCount[1] == 3 || numberCount[2] == 3 || numberCount[3] == 3
                || numberCount[4] == 3 || numberCount[5] == 3 || numberCount[6] == 3 || numberCount[7] == 3
                || numberCount[8] == 3 || numberCount[9] == 3 || numberCount[10] == 3 || numberCount[11] == 3
                || numberCount[12] == 3 || numberCount[13] == 3)
        {
            System.out.println("スリーカード");
        }
        //ツーペア
        //ここだけ良いコードが思いつきませんでした

        //ワンペア
        else if(numberCount[1] == 2 || numberCount[2] == 2 || numberCount[3] == 2
                || numberCount[4] == 2 || numberCount[5] == 2 || numberCount[6] == 2 || numberCount[7] == 2
                || numberCount[8] == 2 || numberCount[9] == 2 || numberCount[10] == 2 || numberCount[11] == 2
                || numberCount[12] == 2 || numberCount[13] == 2)
        {
            System.out.println("ワンペア");
        }
    }

}
