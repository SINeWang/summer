package one.kii.summer.bound.factory;

import one.kii.summer.bound.Context;
import one.kii.summer.bound.Request;
import one.kii.summer.bound.Response;
import one.kii.summer.bound.Summary;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class ResponseFactory {

    private static <U, V extends Response> V build(Request request, V response, U data) {
        Context context = new Context();
        BeanUtils.copyProperties(context, request.getContext());
        context.setResponseId(UUID.randomUUID().toString());
        response.setContext(context);

        BeanUtils.copyProperties(data, response);
        return response;
    }

    public static <U, V extends Response> V accepted(Request form, Class<V> responseClass, U data) {
        V response = null;
        try {
            response = responseClass.newInstance();
            Summary summary = new Summary();
            summary.setStatus(Summary.Status.ACCEPTED);
            summary.setTime(new Date());
            response.setSummary(summary);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return build(form, response, data);
    }

    public static <U, V extends Response> V rejected(Request form, Class<V> responseClass, U data, String... reasons) {
        V response = null;
        try {
            response = responseClass.newInstance();
            Summary summary = new Summary();
            summary.setStatus(Summary.Status.REJECTED);
            summary.setTime(new Date());
            summary.setReasons(Arrays.asList(reasons));
            response.setSummary(summary);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return build(form, response, data);
    }
}
