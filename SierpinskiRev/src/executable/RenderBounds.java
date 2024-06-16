/**
 * @author Gjin Rexhaj
 */

package executable;

/**
 * Class used to create a rectangle which bounds an equilateral triangle.
 * Objects of this class contain a {@code width} and {@code height} int variable, with getter and setter methods.
 */
public class RenderBounds {
  
  private int width;
  private int height;
  
  /**
   * {@code RenderBounds} object constructor
   * @param input The {@code width} of the {@code RenderBounds} object is set directly equal to this parameter.
   * The Height is calculated by multiplying this parameter by (sqrt(3))/(2)
   */
  public RenderBounds(int input) {
    width = input;
    height = (int) (this.width *(Math.sqrt(3)/2));
  }
  
  /**
   * {@code RenderBounds} object default constructor
   */
  public RenderBounds() {
  
  }
  
  /**
   * Sets the {@code width} value (and subsequently the height)
   * @param input is the inputted width. The {@code height} is calculated by multiplying this parameter by (sqrt(3))/(2)
   */
  public void setWidth(int input) {
    width = input;
    height = (int) (this.width *(Math.sqrt(3)/2));
  }
  
  /**
   * Returns the width of a {@code RenderBounds} object
   * @return width
   */
  public int getWidth() {
    return width;
  }
  
  /**
   * Returns the height of a {@code RenderBounds} object
   * @return height
   */
  public int getHeight() {
    return height;
  }
  
  /**
   * Returns the center of a {@code RenderBounds} object
   * @return width/2
   */
  public int getCenter() {
    return width / 2;
  }
  
  /**
   * Calculates the horizontal margin of the {@code RenderBounds} object with respect to the resolution
   * of the drawing canvas
   * @param parentWidth Width of the drawing canvas
   * @return (parentWidth/2)-(width/2)
   */
  public int calculateHorizontalMargin(int parentWidth) {
    return (parentWidth / 2) - (width / 2);
  }
}
