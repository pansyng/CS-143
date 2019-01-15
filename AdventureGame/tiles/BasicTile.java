package tiles;

import game.Game;

public abstract class BasicTile
{
	private TileType type;
	
	public BasicTile(TileType type)
	{
		this.type = type;
	}
	
	public TileType getType()
	{
		return type;
	}
	
	public String toString()
	{
		return type.toString().substring(0, 1);
	}
	
	public abstract void visitTile(Game game);
}
