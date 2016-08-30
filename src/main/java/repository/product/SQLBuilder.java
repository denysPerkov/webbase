package repository.product;

import repository.product.Filter;
import util.Getter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SQLBuilder {

    private Filter filter;
    private String query;

    public SQLBuilder(Filter filter){
        this.filter = filter;
    }

    public void setQuery(String fpartQuery) throws InvocationTargetException, IllegalAccessException {
        StringBuilder builder = new StringBuilder(fpartQuery);
        boolean flagWhere = true;

        for(Method method : filter.getClass().getMethods()) {
            if (method.isAnnotationPresent(Getter.class)) {
                if(method.getReturnType().toString().endsWith("String") && method.invoke(filter) != null ){
                    String fieldValue = (String) method.invoke(filter);
                    String anotatRelation = method.getAnnotation(Getter.class).relation();
                    String anotatName = method.getAnnotation(Getter.class).name();
                    if(flagWhere){
                        builder.append(" WHERE");
                        flagWhere = false;
                    }
                    builder.append(appendQuery(anotatName, anotatRelation, fieldValue, "AND"));
                }

                if(method.getReturnType().toString().endsWith("String;") && method.invoke(filter) != null ){
                    String[] fieldValue = (String[]) method.invoke(filter);
                    String anotatRelation = method.getAnnotation(Getter.class).relation();
                    String anotatName = method.getAnnotation(Getter.class).name();
                    if(flagWhere){
                        builder.append(" WHERE");
                        flagWhere = false;
                    }
                    builder.append("(");
                    for(int i = 0; i < fieldValue.length; i++) {
                        if(i < fieldValue.length - 1){
                            builder.append(appendQuery(anotatName, anotatRelation, fieldValue[i], "OR"));
                        }else{
                            builder.append(appendQuery(anotatName, anotatRelation, fieldValue[i], ""));
                        }

                    }

                    builder.append(") AND");
                }

                if(method.getReturnType().toString().endsWith("double") && (double) method.invoke(filter) != 0.0 ){
                    double fieldValue = (double) method.invoke(filter);
                    String anotatRelation = method.getAnnotation(Getter.class).relation();
                    String anotatName = method.getAnnotation(Getter.class).name();
                    if(flagWhere){
                        builder.append(" WHERE");
                        flagWhere = false;
                    }
                    builder.append(appendQuery(anotatName, anotatRelation, String.valueOf(fieldValue), "AND"));
                }
            }
        }
        query = offExtra(builder);
    }

    public void setOrderByASC(String column){
        this.query = this.query.concat(" ORDER BY " + column);
    }

    public void orderByDESC(String column){
        this.query = this.query.concat(" ORDER BY " + column + " DESC");
    }



    public  void setQueryWithOffset(int start, int finish){
        this.query = query.concat(" limit " + start + " , " + finish);
    }

    public String getQuery(){
       return query;
    }

    private String offExtra(StringBuilder builder){

        if(builder.toString().endsWith("AND")){
            int start = builder.lastIndexOf("AND");
            int finish = builder.length();
            builder.delete(start - 1, finish);
        }

        if(builder.toString().endsWith("OR)")){
            int start = builder.lastIndexOf("OR)");
            int finish = builder.length();
            builder.delete(start - 1, finish);
        }

        return builder.toString();
    }

    private StringBuilder appendQuery(String anotName, String anotRelation, String field, String logic){
        StringBuilder builder = new StringBuilder();
        builder.append(" " + anotName);
        builder.append(" " + anotRelation);
        builder.append(" '" + field + "'");
        builder.append(" " + logic);

        return builder;
    }
}
