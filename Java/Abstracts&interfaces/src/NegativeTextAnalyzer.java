public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private final String[] keywords;

    public NegativeTextAnalyzer(){
        this.keywords = new String[] {":(", "=(", ":|"};
    }
    @Override
    public String[] getKeywords() {
        return keywords;
    }

    @Override
    public Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
