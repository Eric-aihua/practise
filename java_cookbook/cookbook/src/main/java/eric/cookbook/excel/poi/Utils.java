package eric.cookbook.excel.poi;
/**
 * 
 */


/**
 * @author Eric.sun
 * @created 2014-5-21
 */
public class Utils {

    /**
     * get postfix of the path
     * @param path
     * @return
     */
    public static String getPostfix(String path) {
        if (path == null || Common.EMPTY.equals(path.trim())) {
            return Common.EMPTY;
        }
        if (path.contains(Common.POINT)) {
            return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
        }
        return Common.EMPTY;
    }
}
