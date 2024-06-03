// custom class, objects of this class contain a Width and Height int variable, with get/set methods
public class RenderBounds {

  private int size;
  private int height;
  
  public RenderBounds(int size) {
    size = this.size;
    height = (int) (this.size *(Math.sqrt(3)/2));
  }
  
  public RenderBounds() {
  
  }
  
  public void setSize(int input) {
    size = input;
    height = (int) (this.size *(Math.sqrt(3)/2));
  }
  
  public int getSize() {
    return size;
  }
  
  public int getHeight() {
    return height;
  }
  
  
  public int getCenter() {
    return size / 2;
  }
  
  public int calculateHorizontalMargin(int parentWidth) {
    return (parentWidth / 2) - (size / 2);
  }
}
