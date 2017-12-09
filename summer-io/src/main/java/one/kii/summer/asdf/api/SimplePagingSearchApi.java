package one.kii.summer.asdf.api;

import lombok.Data;
import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.context.ReadContext;

import java.util.List;


public interface SimplePagingSearchApi<R, F> extends PagingSearchApi<R, ReadContext, F> {


    @Data
    class Receipt<R> {
        List<R> data;
        Integer total;
    }

    @Data
    class Paging {

        @MayHave
        Integer begin;

        @MayHave
        Integer size;

        @MayHave
        String sort;

        @MayHave
        String orderBy;
    }
}
