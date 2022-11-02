public abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();
    protected abstract Label getLabel();

    @Override
    public Label processText(String text) {
        for (String kw : getKeywords()) {
            if (text.contains(kw)) return getLabel();
        }
        return Label.OK;
    }
}
