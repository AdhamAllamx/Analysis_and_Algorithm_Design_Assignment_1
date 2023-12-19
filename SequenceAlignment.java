import java.util.HashMap;
import java.util.Map;

public class SequenceAlignment {

    public static String[] sequenceAlignment(String x, String y, Double[][] scoringMatrix) {
        int n = x.length();
        int m = y.length();
        char[] nucleotides = {'A', 'G', 'T', 'C', '-'};
        Map<Character, Integer> charToIndexMap = new HashMap<>();
        for (int i = 0; i < nucleotides.length; i++) {
            charToIndexMap.put(nucleotides[i], i);
        }
        double[][] derivedScoreMatrix = new double[n+1][m+1];

        for (int i=0; i<=n; i++) {
            for (int j= 0; j<=m; j++) {
                if (i ==0 || j ==0) {
                    derivedScoreMatrix[i][j] = 0.0;
                } else {
                    double matchedScore = derivedScoreMatrix[i-1][j-1] + scoringMatrix[charToIndexMap.get(x.charAt(i-1))][charToIndexMap.get(y.charAt(j-1))];
                    double dashXScore = derivedScoreMatrix[i-1][j] + scoringMatrix[charToIndexMap.get(x.charAt(i-1))][charToIndexMap.get('-')];
                    double dashYScore = derivedScoreMatrix[i][j - 1] +scoringMatrix[charToIndexMap.get('-')][charToIndexMap.get(y.charAt(j-1))];
//                    if( i == 1 && j == 1) {
//                    	System.out.println(matchedScore);
//                    	System.out.println(dashXScore);
//                    	System.out.println(dashYScore);
//                    }
                    derivedScoreMatrix[i][j] = Math.max((Math.max(matchedScore, dashXScore)), dashYScore);
                    System.out.println("The score in i = " + i + " and j = " + j + " is: " + derivedScoreMatrix[i][j]);
                }
            }
        }
        
        StringBuilder row_X = new StringBuilder();
        StringBuilder row_Y = new StringBuilder();
        int i = n, j = m;

        while (i > 0 || j > 0) {

            if (i > 0 && j > 0 && derivedScoreMatrix[i][j] == derivedScoreMatrix[i-1][j-1] +scoringMatrix[charToIndexMap.get(x.charAt(i-1))][charToIndexMap.get(y.charAt(j-1))]) {
            	 row_X.insert(0, x.charAt(i-1));
            	row_Y.insert(0, y.charAt(j-1));
                i--;
                j--;

            } else if (i > 0 && derivedScoreMatrix[i][j] == derivedScoreMatrix[i-1][j]+scoringMatrix[charToIndexMap.get(x.charAt(i-1))][charToIndexMap.get('-')]) {
            	 row_X.insert(0, x.charAt(i-1));
            	row_Y.insert(0, '-');
                i--;

            } else {
            	row_X.insert(0, '-');
            	row_Y.insert(0, y.charAt(j-1));
                j--;
            }
        }
//        System.out.println("Aligned x: " + row_X.toString());
//        System.out.println("Aligned y: " + row_Y.toString());
        String[] result = new String[2];
        result[0]= row_X.toString();
        result[1]= row_Y.toString();
        return result;
    }
    //created for testing the final and score and compare it with output in the assignment  
    private static double calculateAlignmentScore(String x, String y, Double[][] scoringMatrix) {
        double score = 0.0;
        char[] nucleotides = {'A', 'G', 'T', 'C', '-'};
        Map<Character, Integer> charToIndexMap = new HashMap<>();
        for (int i = 0; i < nucleotides.length; i++) {
            charToIndexMap.put(nucleotides[i], i);
        }
        for (int i = 0; i < x.length(); i++) {
            char charX = x.charAt(i);
            char charY = y.charAt(i);
            int indexX, indexY;
            indexX = charToIndexMap.get(charX);
            indexY = charToIndexMap.get(charY);

            score += scoringMatrix[indexX][indexY];
        }
        return score;
    }

    public static void main(String[] args) {
        String x = "TCCCAGTTATGTCAGGGGACACGAGCATGCAGAGAC";
      String y = "AATTGCCGCCGTCGTTTTCAGCAGTTATGTCAGATC";
//         String x = "ATGCC";
//         String y = "TACGCA";


        Double[][] scoringMatrix = {
                {1.0, -0.8, -0.2, -2.3, -0.6},
                {-0.8, 1.0, -1.1, -0.7, -1.5},
                {-0.2, -1.1, 1.0, -0.5, -0.9},
                {-2.3, -0.7, -0.5, 1.0, -1.0},
                {-0.6, -1.5, -0.9, -1.0, Double.NaN}
        };

        String[] result = sequenceAlignment(x, y, scoringMatrix);
         for(int i = 0 ; i<result.length;i++) {
        	 System.out.println(result[i]);
         }
//         String x1 = "--T--CC-C-AGT--TATGT-CAGGGGACACG-A-GCATGCAGA-GAC";
//         String y2 = "AATTGCCGCC-GTCGT-T-TTCAG----CA-GTTATG-T-CAGAT--C";
         //created this method in order to caluclate the final score and test 
         double alignmentScore = calculateAlignmentScore(result[0], result[1], scoringMatrix);
//         double alignmentScore2 = calculateAlignmentScore(x1, y2, scoringMatrix);

         System.out.println("Alignment Score: " + alignmentScore);
//         System.out.println("Alignment Score: " + alignmentScore2);

    }
}
