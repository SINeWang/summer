package one.kii.summer.asdf.api;

import lombok.Data;
import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Panic;

import java.util.List;

/**
 * Created by WangYanJiong on 22/07/2017.
 */
public interface PagingSearchApi<R, C extends ReadContext, F> {

    Receipt<R> search(C context, F form, Paging paging) throws BadRequest, Panic;


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
