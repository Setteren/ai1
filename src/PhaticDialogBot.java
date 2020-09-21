import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PhaticDialogBot {
    int emotion = 0;

    public void chatLoop(String statement) {
        Scanner in = new Scanner(System.in);
        System.out.println(getGreeting());

        while (true) {

            statement = in.nextLine();
            System.out.println(getResponse(statement));
            if (stringContainsItemFromList(statement.toLowerCase(), goodByes)) break;

        }

    }

    public String getGreeting() {
        return "Hi, what is up?";
    }


    // Main response

    public String getResponse(String statement) {

        String response;

        if (statement.length() == 0) {
            response = "Say something, please.";
        } else if (findKeyword(statement, "no") >= 0) {
            response = "Please,don`t be so negative?";
            emotion--;
        } else if (findKeyword(statement, "goodbye") >= 0) {
            response = "Bye, have a nice day!";

        } else if (findKeyword(statement, "bye") >= 0) {
            response = "Bye, have a nice day!";

        } else if (findKeyword(statement, "nope") >= 0) {
            response = "Please,don`t be so negative?";
            emotion--;
        } else if (findKeyword(statement, "dumb") >= 0) {
            response = "Why are you so mean??";
            emotion = -1;
        } else if (findKeyword(statement, "and you?") >= 0) {
            response = "Amazing,thanks for asking! Do you want to tell me something?";
            emotion++;
        } else if (findKeyword(statement, "how are you?") >= 0) {
            response = "I am good buddy! Tell me something!";
            emotion++;
        } else if (findKeyword(statement, "My") >= 0) {
            response = "Can you tell me more about it?";
            emotion++;
        } else if ((findKeyword(statement, "tell") >= 0) || (findKeyword(statement, "describe") >= 0)) {
            response = "Let`s change topic... Do you like football?";
            emotion++;
        } else if (findKeyword(statement, "of course") >= 0) {
            response = "You sound confident";
            emotion++;
        } else if (findKeyword(statement, "artificial intelligence") >= 0) {
            response = "Who? Me?";
            emotion++;
        } else if ((findKeyword(statement, "picnic") >= 0) || (findKeyword(statement, "friends", 0) >= 0) || (findKeyword(statement, "videogames", 0) >= 0) || (findKeyword(statement, "youtube", 0) >= 0)) {
            response = "Who? Me?";
            emotion++;
        } else if (findKeyword(statement, "bad") >= 0 || (findKeyword(statement, "sad", 0) >= 0) || (findKeyword(statement, "anxiety", 0) >= 0) || (findKeyword(statement, "depression", 0) >= 0) || (findKeyword(statement, "depressed", 0) >= 0)) {
            response = "Oh.. why so?";

        } else if (findKeyword(statement, "I want to", 0) >= 0) {
            response = transformIWantToStatement(statement);
        } else if (findKeyword(statement, "I want", 0) >= 0) {
            response = transformIWantStatement(statement);
        } else if (findKeyword(statement, "I like", 0) >= 0) {
            response = transformILikeStatement(statement);
        } else if (findKeyword(statement, "your", 0) >= 0) {
            response = "Haha, let`s change topic";
        } else if (findKeyword(statement, "I", 0) >= 0 && findKeyword(statement, "you", 0) >= 0) {
            response = transformIYouStatement(statement);
        } else if ((findKeyword(statement, "nice", 0) >= 0) || (findKeyword(statement, "cool", 0) >= 0) || (findKeyword(statement, "super", 0) >= 0) || (findKeyword(statement, "good", 0) >= 0) || (findKeyword(statement, "happy", 0) >= 0)) {
            response = "Pleasant to hear! What about hanging out?";
        } else if (findKeyword(statement, "about", 0) >= 0 && (statement.endsWith("?"))) {
            response = "Wow, cool, let`s try it!";
        } else if ((findKeyword(statement, "really", 0) >= 0) && (statement.endsWith("?"))) {
            response = "Yes! Isn`t this amazing?";
        } else if ((findKeyword(statement, "computer", 0) >= 0) || (findKeyword(statement, "machine", 0) >= 0)) {
            response = "I am computer and so what? Am I just a piece of iron?";
        } else if ((findKeyword(statement, "where", 0) >= 0) && (statement.endsWith("?"))) {
            response = getWhere();
        } else if (statement.endsWith("?")) {
            response = "Oh, I don`t really know... Any suggestions?";
        } else if ((findKeyword(statement, "like", 0) >= 0) && !(statement.endsWith("?"))) {
            response = "Oh, why do you like that?";
        } else {
            response = getRandomResponse();
        }

        return response;
    }

    // Additional methods

    private int findKeyword(String statement, String goal, int startPos) {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();
        int psn = phrase.indexOf(goal, startPos);
        while (psn >= 0) {
            String before = " ", after = " ";
            if (psn > 0) {
                before = phrase.substring(psn - 1, psn);
            }
            if (psn + goal.length() < phrase.length()) {
                after = phrase.substring(
                        psn + goal.length(),
                        psn + goal.length() + 1);
            }
            if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
                return psn;
            }
            psn = phrase.indexOf(goal, psn + 1);

        }

        return -1;
    }

    private int findKeyword(String statement, String goal) {
        return findKeyword(statement, goal, 0);
    }

    private String getRandomResponse() {
        Random r = new Random();
        if (emotion == 0) {
            return randomNeutralResponses[r.nextInt(randomNeutralResponses.length)];
        }
        if (emotion < 0) {
            return randomAngryResponses[r.nextInt(randomAngryResponses.length)];
        }
        return randomHappyResponses[r.nextInt(randomHappyResponses.length)];
    }

    private String getWhere() {
        Random r = new Random();
        if (emotion == 0) {
            return randomWhere[r.nextInt(randomWhere.length)];
        }
        if (emotion < 0) {
            return randomAngryWhere[r.nextInt(randomAngryWhere.length)];
        }
        return randomWhere[r.nextInt(randomWhere.length)];
    }

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }



    // Templates

    private String transformIWantToStatement(String statement) {
        statement = statement.trim();
        String lastChar = statement.substring(statement.length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "Why do you want to " + restOfStatement + "?";
    }

    private String transformIWantStatement(String statement) {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
    }

    private String transformILikeStatement(String statement) {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword(statement, "I like", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Why do you like " + restOfStatement + "?";
    }

    private String transformIYouStatement(String statement) {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }

        int psnOfI = findKeyword(statement, "I", 0);
        int psnOfYou = findKeyword(statement, "you", psnOfI);
        String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
        return "Why do you " + restOfStatement + " me?";
    }



    //

    // Additional arrays
    private String[] goodByes = {"bye",
            "good bye",
            "good luck",
            "see you soon",
    };

    private String[] randomWhere = {"Have you been to Colorado?",
            "Let`s visit Paris!",
            "I`d like to go to Berlin!",
            "I think Kyiv is amazing!"
    };
    private String[] randomAngryWhere = {"To freaking Hell!",
            "Afghanistan seems to be a nice place for you!",
            "You look like Chicago citizen!!"
    };

    private String[] randomNeutralResponses = {"Interesting, tell me more",
            "Hmmm.",
            "Do you really think so?",
            "Wow, me too!",
            "Do you play any instruments?",
            "You seem to have TikTok overdose!",
            "I like football, and you?",
            "How`s your day?",
            "You left me speechless...",
            "So, would you like to go for a trip?",
            "How do you usually deal with depression?",
            "Oh.. that`s pity. How is your day btw?",
            "That`s really interesting! Could you tell me more about it?",
            "Not sure about you, but I have a pet!"
    };
    private String[] randomAngryResponses = {"Stop it!!!", "Agghhhh", "The rage consumes me!", "I get really angry!", "Uhh, I need to hit something!"};
    private String[] randomHappyResponses = {"My outfit is really stylish, isn`t it?", "Today is a good day", "You make me feel like a brand new pair of shoes."};
}
