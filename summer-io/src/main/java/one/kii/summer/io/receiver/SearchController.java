package one.kii.summer.io.receiver;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class SearchController<T> extends ReadController {


    public final static String REQUEST_KEY_QUERY = "q";

    public final static String REQUEST_KEY_PAGE = "page";

    public final static String REQUEST_KEY_SIZE = "size";

    public final static String DEFAULT_PAGE = "0";

    public final static String DEFAULT_SIZE = "30";

    @Autowired
    private T api;



}
