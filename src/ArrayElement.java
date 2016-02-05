import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ArrayElement {
	
	public int value;
	public int xPosition;
	
	private Color color;
	private Color defaultColor = Color.white;
	private boolean isSelected;
	private final int barWidth = 20;
	
	public ArrayElement(int value, int index) {
		this.value = 10*(value)+1;;
		this.xPosition = (index * barWidth) + 1;
		
		color = defaultColor;
		isSelected = false;
	}
	
	public void setPosition(int index) {
		this.xPosition = (index * barWidth) + 1;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void draw(Graphics g) {
		
		Graphics2D graphics = (Graphics2D)g;//s.getGraphics();
		graphics.setPaint(color);
		graphics.fill3DRect(xPosition, 0, barWidth, value, false);
	}
	
	public void highlight(boolean isHighlight) {
		if(isSelected)
			return;
		color = isHighlight ? Color.green : defaultColor;
	}
	
	
	public void bright(boolean isBright) {
		if(isSelected)
			return;
		color = isBright ? Color.lightGray : defaultColor;
	}
	
	public void setSorted() {
		color = Color.blue;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		color = isSelected ? Color.red : defaultColor;
	}
}
