package net.unibave.showcase.base.jaxrs;

import java.util.List;

public final class Page<T> {

    private List<T> content;

    private Integer firstResult;

    private Integer maxResults;

    private boolean hasMore;

    private Integer numberOfElements;

    private Long total;

    Page() {
    }

    public List<T> getContent() {
        return content;
    }

    void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public boolean getHasMore() {
        return hasMore;
    }

    void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Long getTotal() {
        return total;
    }

    void setTotal(Long total) {
        this.total = total;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {

        private Page<T> page = new Page<>();

        public Builder<T> content(List<T> content) {
            page.setContent(content);
            return this;
        }

        public Builder<T> firstResult(Integer firstResult) {
            page.setFirstResult(firstResult);
            return this;
        }

        public Builder<T> maxResults(Integer maxResults) {
            page.setMaxResults(maxResults);
            return this;
        }

        public Builder<T> hasMore(boolean hasMore) {
            page.setHasMore(hasMore);
            return this;
        }

        public Builder<T> numberOfElements(Integer numberOfElements) {
            page.setNumberOfElements(numberOfElements);
            return this;
        }

        public Builder<T> total(Long total) {
            page.setTotal(total);
            return this;
        }

        public Page<T> build() {
            return page;
        }

    }

}
