package pathfinder;

import java.util.ArrayList;

import character.Characters;
import main.PlayingCanvas;

public class PathFinder {
	PlayingCanvas pc;
	Node [][] node;
	ArrayList<Node>openList = new ArrayList<>();
	public ArrayList<Node>pathList = new ArrayList<>();
	Node startNode, goalNode,currentNode;
	boolean goalReached = false;
	int step=0;
	
	
	public PathFinder(PlayingCanvas pc) {
		this.pc =pc;
		instNode();
	}
	
	public void instNode() {
		node = new Node[pc.maxWorldCol][pc.maxWorldRow];
		
		int col=0;
		int row=0;
		
		while(col < pc.maxWorldCol && row  < pc.maxWorldRow) {
			node[col][row] = new Node(col,row);
			col++;
			if(col == pc.maxWorldCol) {
				col=0;
				row++;
			}
		}
	}
	
	public void resetNode(){
		int col=0;
		int row=0;
		
		while(col < pc.maxWorldCol && row  < pc.maxWorldRow) {
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			col++;
			if(col == pc.maxWorldCol) {
				col=0;
				row++;
			}
		}
		openList.clear();
		pathList.clear();
		goalReached=false;
		step=0;
	}
	
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Characters character) {
		resetNode();
		startNode= node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		
		int col=0;
		int row=0;
		
		while(col < pc.maxWorldCol && row  < pc.maxWorldRow) {
			int rectNum = pc.rectM.mapRectNum[pc.currentMap][col][row];
			if(pc.rectM.rect[rectNum].collision == true) {
				node[col][row].solid = true;
			}
			for(int i=0; i < pc.iRect[1].length; i++) {
				if(pc.iRect[pc.currentMap][i] != null && pc.iRect[pc.currentMap][i].destruct == true) {
					int itCol = pc.iRect[pc.currentMap][i].worldX/pc.rectSize;
					int itRow = pc.iRect[pc.currentMap][i].worldY/pc.rectSize;
					node[itCol][itRow].solid = true;
				}
			}
			
			getCost(node[col][row]);
			col++;
			if(col == pc.maxWorldCol) {
				col=0;
				row++;
			}
		}
	}
	
	public void openNode(Node node) {
		if(node.open == false && node.checked == false && node.solid == false) {
			node.open= true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	
	public void getCost(Node node) {
		//G Cost
		int xDist = Math.abs(node.col - startNode.col);
		int yDist = Math.abs(node.row - startNode.row);
		node.gCost = xDist + yDist;
		
		//H Cost
		xDist = Math.abs(node.col - goalNode.col);
		yDist = Math.abs(node.row - goalNode.row);
		node.hCost = xDist + yDist;
		
		//F Cost
		node.fCost = node.gCost + node.hCost;
	}
	
	public void trackThePath() {
		
		Node current = goalNode;
		
		while(current != startNode) {
			pathList.add(0,current);
			current = current.parent;
		}
	}
	
	public boolean search() {
		while(goalReached == false && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			
			currentNode.checked = true;
			openList.remove(currentNode);
			
			//Up node
			if(row - 1 >= 0) {
				openNode(node[col][row-1]);
			}
			//left node
			if(col - 1 >= 0) {
				openNode(node[col-1][row]);
			}
			//down node
			if(row + 1 < pc.maxWorldRow) {
				openNode(node[col][row+1]);
			}
			
			//right node
			if(col + 1 < pc.maxWorldCol) {
				openNode(node[col+1][row]);
			}
			
			//find the best node
			
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for(int i=0; i < openList.size(); i++) {
				
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				}else if(openList.get(i).fCost == bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			
			if(openList.size() == 0) {
				break;
			}
			
			currentNode = openList.get(bestNodeIndex);
			if(currentNode == goalNode) {
				goalReached=true;
				trackThePath();
			}
			step++;
		}
		return goalReached;
	}
}
