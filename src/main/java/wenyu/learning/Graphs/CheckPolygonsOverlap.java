package wenyu.learning.Graphs;

import java.awt.*;

/**
 * Created by Wenyu on 12/12/16.
 */
public class CheckPolygonsOverlap {
//    boolean isPolygonsIntersecting(Polygon a, Polygon b)
//    {
//        for (int x=0; x<2; x++)
//        {
//            Polygon polygon = (x==0) ? a : b;
//
//            for (int i1=0; i1<polygon.getPoints().length; i1++)
//            {
//                int   i2 = (i1 + 1) % polygon.getPoints().length;
//                Point p1 = polygon.getPoints()[i1];
//                Point p2 = polygon.getPoints()[i2];
//
//                Point normal = new Point(p2.y - p1.y, p1.x - p2.x);
//
//                double minA = Double.MAX_VALUE;
//                double maxA = Double.MIN_VALUE;
//
//                for (Point p : a.getPoints())
//                {
//                    double projected = normal.x * p.x + normal.y * p.y;
//
//                    if (projected < minA)
//                        minA = projected;
//                    if (projected > maxA)
//                        maxA = projected;
//                }
//
//                double minB = Double.MAX_VALUE;
//                double maxB = Double.MIN_VALUE;
//
//                for (Point p : b.getPoints())
//                {
//                    double projected = normal.x * p.x + normal.y * p.y;
//
//                    if (projected < minB)
//                        minB = projected;
//                    if (projected > maxB)
//                        maxB = projected;
//                }
//
//                if (maxA < minB || maxB < minA)
//                    return false;
//            }
//        }
//
//        return true;
//    }
}
