// custom class used to create a rectangle which bounds an equilateral triangle
// objects of this class contain a Width and Height int variable, with get/set methods
package executable;

public class RenderBounds {

  private int width;
  private int height;
  
  public RenderBounds(int input) {
    width = input;
    height = (int) (this.width *(Math.sqrt(3)/2));
  }
  
  public RenderBounds() {
  
  }
  
  public void setWidth(int input) {
    width = input;
    height = (int) (this.width *(Math.sqrt(3)/2));
  }
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
  
  public int getCenter() {
    return width / 2;
  }
  
  public int calculateHorizontalMargin(int parentWidth) {
    return (parentWidth / 2) - (width / 2);
  }
}
