package EarthquakeJavaFX;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * CustomCircleMarkerLayer is a MapLayer that displays a red circle marker on the map.
 */
public class CustomCircleMarkerLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final Circle circle;

    /**
     * Constructs a CustomCircleMarkerLayer with the specified map point.
     *
     * @param mapPoint the map point (latitude and longitude) where the circle marker should be displayed
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomCircleMarkerLayer(MapPoint mapPoint) {
        this.mapPoint = mapPoint;

        // Create a red circle with a radius of 5
        this.circle = new Circle(5, Color.RED);

        // Add the circle to the MapLayer if the mapPoint coordinates are valid
        if (mapPoint.getLatitude() != 0 && mapPoint.getLongitude() != 0) {
            this.getChildren().add(circle);
        }
    }

    /**
     * This method is called whenever the map is refreshed.
     * It updates the position of the circle marker based on the map point coordinates.
     */
    @Override
    protected void layoutLayer() {
        // Convert the MapPoint to a Point2D
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        // Move the circle marker to the specified coordinates
        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
    }
}