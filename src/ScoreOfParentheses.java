public class ScoreOfParentheses
{
    /*
        Given a balanced parentheses string s, return the score of the string.
        The score of a balanced parentheses string is based on the following rule:
        "()" has score 1.
        AB has score A + B, where A and B are balanced parentheses strings.
        (A) has score 2 * A, where A is a balanced parentheses string.

        Example 1:
        Input: s = "()"
        Output: 1

        Example 2:
        Input: s = "(())"
        Output: 2

        Example 3:
        Input: s = "()()"
        Output: 2


        Constraints:

        2 <= s.length <= 50
        s consists of only '(' and ')'.
        s is a balanced parentheses string.
     */

    public int scoreOfParentheses(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                bal++;
            } else {
                bal--;
                if (S.charAt(i-1) == '(')
                    ans += (int) Math.pow(2,bal);
            }
        }

        return ans;
    }
}
