package org.msn;

import java.util.Random;


public class GenerateStepsAlgorytm {
	
	private int currentRowPosition;
	private int currentColumPosition;
	private int destinationRowPostion;
	private int destinationColumnPostion;
	
	private int networkSize;
	
	private int currentHorizontalStep;
	private int currentVerticalStep;
	private Random rand;
	
	
	public GenerateStepsAlgorytm(int currentRowPosition, int currentColumnPosition, int destinationRowPosition,
			int destinationColumnPosition, int networkSize){
		this.currentRowPosition=currentRowPosition;
		this.currentColumPosition=currentColumnPosition;
		this.destinationRowPostion=destinationRowPosition;
		this.destinationColumnPostion=destinationColumnPosition;
		this.networkSize=networkSize;
	}
		
	public Step getNextStep(){
		
		this.rand = new Random();
		calculateSteps();
		
		Step step = Step.NO_STEP;
		
		if(currentVerticalStep==0 && currentHorizontalStep==0)
			return step;
		
		boolean rowEven , columnEven;
		
		if(currentRowPosition%2==0)
			rowEven=true;
		else
			rowEven=false;
		
		if(currentColumPosition%2==0)
			columnEven=true;
		else
			columnEven=false;
		
		//przypadek wspolrzednych parzysto parzystych - gora , prawo
		if(rowEven==true & columnEven==true){
			
			//przypadek gdy nie musimy zmieniac wiersza
			if(currentHorizontalStep==0){
				
				if(currentVerticalStep<0)
					step=Step.RIGHT;
				else{
					int goByLine=  networkSize-Math.abs(currentVerticalStep);
					if(goByLine==2+Math.abs(currentVerticalStep)){
						int i=rand.nextInt(100);
						if(i<50)
							step=Step.RIGHT;
						else
							step=Step.TOP;
					}
					else if(goByLine<2+Math.abs(currentVerticalStep))
						step=Step.RIGHT;
					else
						step=Step.TOP;
				}		
			}
			
			//przypadek gdy w nie musimy zmieniac kolumny
			else if(currentVerticalStep==0){
				if(currentHorizontalStep>0)
					step=Step.TOP;
				else{
					int goByLine= networkSize - Math.abs(currentHorizontalStep);
					if(goByLine==2+Math.abs(currentHorizontalStep)){
						int i=rand.nextInt(100);
						if(i<50)
							step=Step.RIGHT;
						else
							step=Step.TOP;
					}
					else if(goByLine<2+Math.abs(currentHorizontalStep))
						step=Step.TOP;
					else
						step=Step.RIGHT;
					}
						
			}
			//jeżeli musisz iśc w góre to idź w górę
			else if(currentHorizontalStep>0){
				step=Step.TOP;
			}
			//jeżeli musisz iśc w prawo gdy jest parzysto parzyste to zawsze idź w prawo ( chyba ze jest w gore)
			else if(currentVerticalStep<0){
				step=Step.RIGHT;
			}
			//przypadek gdy trzeba isc w lewo i w dół
			else if(currentHorizontalStep<0 && currentVerticalStep>0){
				//powiedzmy ze wtedy zawsze idziemy w górę
				step=Step.RIGHT;
			}
		
		}
			
		/////////////////////////////////////////////////	
		//przypadek wspolrzednych parzysto nieparzystych - dol prawo
		if(rowEven==true & columnEven==false){
					
			//przypadek gdy  nie musimy zmieniac wiersza
			if(currentHorizontalStep==0){
				
				if(currentVerticalStep<0)
					step=Step.RIGHT;
				else{
					int goByLine=  networkSize-Math.abs(currentVerticalStep);
					if(goByLine==2+Math.abs(currentVerticalStep)){
						int i=rand.nextInt(100);
						if(i<50)
							step=Step.RIGHT;
						else
							step=Step.DOWN;
					}
					else if(goByLine<2+Math.abs(currentVerticalStep))
						step=Step.RIGHT;
					else
						step=Step.DOWN;
				}		
			}
			
			//przypadek gdy nie musimy zmieniac kolumny
			else if(currentVerticalStep==0){
				if(currentHorizontalStep<0)
					step=Step.DOWN;
				else{
					int goByLine= networkSize - Math.abs(currentHorizontalStep);
					if(goByLine==2+Math.abs(currentHorizontalStep)){
						int i=rand.nextInt(100);
						if(i<50)
							step=Step.RIGHT;
						else
							step=Step.DOWN;
					}
					else if(goByLine<2+Math.abs(currentHorizontalStep))
						step=Step.DOWN;
					else
						step=Step.RIGHT;
					}	
			}
			
			//jeżeli musisz iśc w dól to idz
			else if(currentHorizontalStep<0){
				step=Step.DOWN;
			}
			//jeżeli musisz iśc w prawo gdy jest parzysto parzyste to zawsze idź w prawo ( chyba ze jest w dol)
			else if(currentVerticalStep<0){
				step=Step.RIGHT;
			}
			//przypadek gdy trzeba isc w lewo i w gore
			else if(currentHorizontalStep>0 && currentVerticalStep>0){
				//powiedzmy ze wtedy zawsze idziemy w dół
				step=Step.DOWN;
			}
			
		}
		///////////////////////////////
		//przypadek wspolrzednych nieparzystych nieparzystych - dol , lewo
		if(rowEven==false & columnEven==false){
	
			//przypadek gdy  nie musimy zmieniac wiersza
			if(currentHorizontalStep==0){
				
				if(currentVerticalStep>0)
					step=Step.LEFT;
				else{
					int goByLine=  networkSize-Math.abs(currentVerticalStep);
					if(goByLine==2+Math.abs(currentVerticalStep)){
						int i=rand.nextInt(100);
						if(i<50)
							step=Step.LEFT;
						else
							step=Step.DOWN;
					}
					else if(goByLine<2+Math.abs(currentVerticalStep))
						step=Step.LEFT;
					else
						step=Step.DOWN;
				}		
			}
			
			//przypadek gdy nie musimy zmieniac kolumny
			else if(currentVerticalStep==0){
				if(currentHorizontalStep<0)
					step=Step.DOWN;
				else{
					int goByLine= networkSize - Math.abs(currentHorizontalStep);
					if(goByLine==2+Math.abs(currentHorizontalStep)){
						int i=rand.nextInt(100);
						if(i<50)
							step=Step.LEFT;
						else
							step=Step.DOWN;
					}
					else if(goByLine<2+Math.abs(currentHorizontalStep))
						step=Step.DOWN;
					else
						step=Step.LEFT;
					}	
			}
			
			//jeżeli musisz iśc w dól to idz
			else if(currentHorizontalStep<0){
				step=Step.DOWN;
			}
			//jeżeli musisz iśc w lewo gdy jest parzysto parzyste to zawsze idź w lewo ( chyba ze jest w dol)
			else if(currentVerticalStep>0){
				step=Step.LEFT;
			}
			//przypadek gdy trzeba isc w prawo i w gore
			else if(currentHorizontalStep>0 && currentVerticalStep<0){
				//powiedzmy ze wtedy zawsze idziemy w lewo
				step=Step.LEFT;
			}
			
		}
		/////////////////////////////////////////////////////
		//przypadek wspolrzednych nieparzystych parzystych - lewo , gora
		if(rowEven==false & columnEven==true){
	
			//przypadek gdy  nie musimy zmieniac wiersza
			if(currentHorizontalStep==0){
				
				if(currentVerticalStep>0)
					step=Step.LEFT;
				else{
					int goByLine=  networkSize-Math.abs(currentVerticalStep);
					if(goByLine==2+Math.abs(currentVerticalStep)){
						int i=rand.nextInt(100);
						if(i<50)
							step=Step.LEFT;
						else
							step=Step.TOP;
					}
					else if(goByLine<2+Math.abs(currentVerticalStep))
						step=Step.LEFT;
					else
						step=Step.TOP;
				}		
			}
			
			//przypadek gdy nie musimy zmieniac kolumny
			else if(currentVerticalStep==0){
				if(currentHorizontalStep>0)
					step=Step.TOP;
				else{
					int goByLine= networkSize - Math.abs(currentHorizontalStep);
					if(goByLine==2+Math.abs(currentHorizontalStep)){
						int i=rand.nextInt(100);
						if(i<50)
							step=Step.LEFT;
						else
							step=Step.TOP;
					}
					if(goByLine<2+Math.abs(currentHorizontalStep))
						step=Step.TOP;
					else
						step=Step.LEFT;
					}	
			}
			
			//jeżeli musisz iśc w gore to idz
			else if(currentHorizontalStep>0){
				step=Step.TOP;
			}
			//jeżeli musisz iśc w lewo gdy jest parzysto parzyste to zawsze idź w lewo ( chyba ze jest w gore)
			else if(currentVerticalStep>0){
				step=Step.LEFT;
			}
			//przypadek gdy trzeba isc w prawo i w dol
			else if(currentHorizontalStep<0 && currentVerticalStep<0){
				//powiedzmy ze wtedy zawsze idziemy w lewo
				step=Step.TOP;
			}
			
			
		}

		
		return step;
	}
	
	private void calculateSteps(){
		//ujemne ruch w dół
		//dodatnie ruch w górę
		currentHorizontalStep = currentRowPosition - destinationRowPostion;
		
		//sprawdzamy czy nie lepiej tyłem dojść
		if(Math.abs(currentHorizontalStep)>Math.abs(Math.abs(currentHorizontalStep)-networkSize)){
			currentHorizontalStep= (currentHorizontalStep>0) ? currentHorizontalStep-networkSize : networkSize + currentHorizontalStep ;
		}
		
		//ujemne ruch w prawo
		//dodatnie ruch w lewo
		currentVerticalStep = currentColumPosition - destinationColumnPostion;
				
		//sprawdzamy czy nie lepiej tyłem dojść
		if(Math.abs(currentVerticalStep)>Math.abs(Math.abs(currentVerticalStep)-networkSize)){
			currentVerticalStep= (currentVerticalStep>0) ? currentVerticalStep-networkSize : networkSize + currentVerticalStep ;
		}
		//Wiemy juz jak mamy iść, że np. dwa ruchy w góre i 3 w lewo
		
	}
	
	
}

