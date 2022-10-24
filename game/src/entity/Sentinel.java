package entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import Interface.IEntity;
import asset.ImageData;
import component.EntityManager;
import panel.GameDefend;
import util.Rect2;
import util.SizePattern;
import util.Vector2;

public class Sentinel implements IEntity, Serializable, Cloneable {

	private Rect2 rect = new Rect2(SizePattern.tileSize * 2, SizePattern.tileSize * 2);
	private static final long serialVersionUID = 1347943847248129981L;
	private static final Rect2 rectOffSet = new Rect2(-16, -64);
	private long frameStart = GameDefend.getFrameCount();
	private ImageData imageData = new ImageData();
	private EntityManager manager;
	private Vector2 position;

	public Sentinel(Vector2 position, EntityManager manager) {
		this.manager = manager;
		this.position = new Vector2(position.x + rectOffSet.w, position.y + rectOffSet.h);
	}

	private void shoot() {
		this.manager.addEntity(new Bullet(this.manager, position.sum(new Vector2(96, 56))));
	}

	@Override
	public void update() {
		if ((GameDefend.getFrameCount() + this.frameStart) % 90 == 0) {
			System.out.println("atirei: " + GameDefend.getFrameCount());
			this.shoot();
		}
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		try {
			graphics2d.drawImage(ImageIO.read(getClass().getResourceAsStream(this.imageData.ober1)), this.position.x,
					this.position.y, SizePattern.tileSize * 2, SizePattern.tileSize * 2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Vector2 getPosition() {
		return this.position;
	}

	public static Rect2 getRectoffset() {
		return rectOffSet;
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public Rect2 getRect() {
		return this.rect;
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Sentinel clone() {
		try {
			return (Sentinel) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
}
