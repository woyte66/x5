//// X5:  collisions.  bam59cst112day/x5
//// (Assume ball diameter of 30.)

//// GLOBALS:  pool table, 3 colored balls

String title=  "ELASTIC COLLISIONS  (x5day.java)";
String news=   "Use 'r' key to reset.";
String author=  "Your Name";


float left, right, top, bottom;
float middle;

float cueX,  cueY,  cueDX,  cueDY;
float redX,  redY,  redDX,  redDY;
float yelX,  yelY,  yelDX,  yelDY;
float bluX, bluY, bluDX, bluDY;

//// SETUP:  size and table
void setup() {
  size( 600, 400 );
  left=   50;
  right=  width-50;
  top=    100;
  bottom= height-50;
  middle= left + (right-left) / 2;
  //
  reset();
 }
 void reset() {
   cueX=  left + (right-left) / 4;
   cueY=  top + (bottom-top) / 2;
   // Random positions.
   redX=  random( middle,right );   redY=  random( top, bottom );
   yelX=  random( middle,right );   yelY=  random( top, bottom );
   bluX=  random( middle,right );   bluY=  random( top, bottom );
   // Random speeds
   redDX=  random( 1,3 );   redDY=  random( 1,3 );
   yelDX=  random( 1,3 );   redDY=  random( 1,3 );
   bluDX=  random( 1,3 );   bluDY=  random( 1,3 );
 }

//// NEXT FRAME:  table, bounce off walls, collisions, show all
void draw() {
  background( 250,250,200 );
  rectMode( CORNERS );
  table( left, top, right, bottom );
  bounce();
  collisions();
  show();
  messages();
}

//// SCENE:  draw the table with walls
void table( float left, float top, float right, float bottom ) {
  fill( 100, 250, 100 );    // green pool table
  strokeWeight(20);
  stroke( 127, 0, 0 );      // Brown walls
  rect( left-20, top-20, right+20, bottom+20 );
  stroke(0);
  strokeWeight(1);
}

//// ACTION:  bounce off walls, collisions
void bounce() {
  redX += redDX;  if ( redX<left || redX>right ) redDX *= -1;
  redY += redDY;  if ( redY<top || redY>bottom ) redDY *=  -1;

  yelX += yelDX;  if ( yelX<left || yelX>right ) yelDX *= -1;
  yelY += yelDY;  if ( yelY<top || yelY>bottom ) yelDY *=  -1;

}
void collisions() {
  float tmp;
  // Swap velocities!
  if ( dist( redX,redY, yelX,yelY ) < 30 ) {
    tmp=yelDX;  yelDX=redDX;  redDX=tmp;
    tmp=yelDY;  yelDY=redDY;  redDY=tmp;
  }
}

//// SHOW:  balls, messages
void show() {
  fill( 255,255,255 );    ellipse( redX,redY, 30,30 );
  fill( 255,0,0 );    ellipse( redX,redY, 30,30 );
  fill( 255,255,0 );  ellipse( yelX,yelY, 30,30 );
  fill( 0,0,255 );    ellipse( bluX,bluY, 30,30 );
  fill( 255,255,255 );    ellipse( cueX,cueY, 30,30 );
}
void messages() {
  fill(0);
  text( title, width/3, 20 );
  text( news, width/3, 40 );
  text( author, 10, height-10 );
}


//// HANDLERS:  keys, click
void keyPressed() {
  if (key == 'r') {
    reset();
  }
}
