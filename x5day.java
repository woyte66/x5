//// X5:  collisions.
//// (Assume ball diameter of 30.)

//// GLOBALS:  pool table, 3 colored balls

String title=  "ELASTIC COLLISIONS";
String news=   "Use 'r' key to reset.";
String author=  "Emmett";

int balldiam = 30;
float friction = 1.007;

float left, right, top, bottom;
float middle;

float cueX,  cueY,  cueDX,  cueDY;
float redX,  redY,  redDX,  redDY;
float yelX,  yelY,  yelDX,  yelDY;
float bluX, bluY, bluDX, bluDY;

float modX,modY;

float pW = 1;


//// SETUP:  size and table
void setup() {
  size( 600, 400 );
  rectMode( CORNERS );
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
   redX=  random( left,right );   redY=  random( top, bottom );
   yelX=  random( left,right );   yelY=  random( top, bottom );
   bluX=  random( left,right );   bluY=  random( top, bottom );
   // Random speeds
   redDX=  random( -5,5 );   redDY=  random( -5,5 );
   yelDX=  random( -5,5 );   redDY=  random( -5,5 );
   bluDX=  random( -5,5 );   bluDY=  random( -5,5 );
 }

//// NEXT FRAME:  table, bounce off walls, collisions, show all
void draw() {
  background( 250,250,200 );  
  //rectMode( CORNERS );
  table( left, top, right, bottom );
  bounce();
  pointer();
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
  redX += redDX;  if ( redX<=left || redX>=right ) redDX = -redDX;
  redY += redDY;  if ( redY<=top || redY>=bottom ) redDY =  -redDY;
  redDX = redDX/friction;
  redDY = redDY/friction;
  
  
  bluX += bluDX;  if ( bluX<left || bluX>right ) bluDX *= -1;
  bluY += bluDY;  if ( bluY<top || bluY>bottom ) bluDY *=  -1;
  bluDX = bluDX/friction;
  bluDY = bluDY/friction;
  
  yelX += yelDX;  if ( yelX<left || yelX>right ) yelDX *= -1;
  yelY += yelDY;  if ( yelY<top || yelY>bottom ) yelDY *=  -1;
  yelDX = yelDX/friction;
  yelDY = yelDY/friction;
  
  cueX += cueDX;  if ( cueX<left || cueX>right ) cueDX *= -1;
  cueY += cueDY;  if ( cueY<top || cueY>bottom ) cueDY *=  -1;
  cueDX = cueDX/friction;
  cueDY = cueDY/friction;
}
void collisions() {
  float tmp;
  // Swap velocities!
  if ( dist( redX,redY, yelX,yelY ) <= 30 ) {
    tmp=yelDX;  yelDX=+redDX;  redDX=+tmp;
    tmp=yelDY;  yelDY=+redDY;  redDY=+tmp;
  }
  if ( dist( redX,redY, bluX,bluY ) <= 30 ) {
    tmp=bluDX;  bluDX=+redDX;  redDX=+tmp;
    tmp=bluDY;  bluDY=+redDY;  redDY=+tmp;
  }
  if ( dist( bluX,bluY, yelX,yelY ) <= 30 ) {
    tmp=yelDX;  yelDX=+bluDX;  bluDX=+tmp;
    tmp=yelDY;  yelDY=+bluDY;  bluDY=+tmp;
  }
  if ( dist( cueX,cueY, yelX,yelY ) <= 30 ) {
    tmp=yelDX;  yelDX=+cueDX;  cueDX=+tmp;
    tmp=yelDY;  yelDY=+cueDY;  cueDY=+tmp;
  }
  if ( dist( cueX,cueY, bluX,bluY ) <= 30 ) {
    tmp=bluDX;  bluDX=+cueDX;  cueDX=+tmp;
    tmp=bluDY;  bluDY=+cueDY;  cueDY=+tmp;
  }
  if ( dist( cueX,cueY, redX,redY ) <= 30 ) {
    tmp=redDX;  redDX=+cueDX;  cueDX=+tmp;
    tmp=redDY;  redDY=+cueDY;  cueDY=+tmp;
  }
}

//// SHOW:  balls, messages
void show() {
  noStroke();
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

void pointer(){
  fill(0);
  strokeWeight(pW);  
  line(cueX,cueY,mouseX,mouseY);
  strokeWeight(1);
  pW = 1;
}

//// HANDLERS:  keys, click
void mousePressed(){
  cueDX =+ (cueX - mouseX)/50;
  cueDY =+ (cueY - mouseY)/50;
  pW = dist(cueX,cueY,mouseX,mouseY)/25;
      
}

void keyPressed() {
  if (key == 'r') {
    reset();
  }
}
