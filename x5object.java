//// X5:  collisions.  bam59cst112day/x5
//// (Assume ball diameter of 30.)

//// GLOBALS:  pool table, 3 colored balls

String title=  "ELASTIC COLLISIONS  (x5day.java)";
String help=   "Click to shoot crystal ball. \n 'r' key to reset, 'q to quit.";
String news=help;
String author=  "Joe Bloggs";


float west, east, north, south;
float middle;

float crystalX,  crystalY,  crystalDX,  crystalDY;
float bamX,  bamY,  bamDX,  bamDY;
float debX,  debY,  debDX,  debDY;
float adamX, adamY, adamDX, adamDY;


Ball one, two, three, four, five;
int number=0;


//// SETUP:  size and table
void setup() {
  size( 600, 400 );
  
  west=   50;
  east=  width-50;
  north=    100;
  south= height-50;
  middle= west + (east-west) / 2;
  //
  reset();
  news=help + "\n     (+ or - changes brown ball speed.)";
 }
 void reset() {
   // crystal ball at rest.
   crystalX=  west + (east-west) / 4;
   crystalY=  north + (south-north) / 2;
   crystalDX=  crystalDY= 0;
   // Random positions.
   bamX=  random( middle,east );   bamY=  random( north, south );
   debX=  random( middle,east );   debY=  random( north, south );
   adamX=  random( middle,east );   adamY=  random( north, south );
   // Random speeds
   bamDX=  random( 1,3 );   bamDY=  random( 1,3 );
   debDX=  random( 1,3 );   bamDY=  random( 1,3 );
   adamDX=  random( 1,3 );   adamDY=  random( 1,3 );   
  //// NEW OBJECTS ////
  number=1;
  one=  new Ball( 255,200,200 );
  two=  new Ball( 200,255,200 );
  three=  new Ball( 200,200,255 );
  four=  new Ball( 255,255,100 );
  five=  new Ball( 255,100,255 );
 }

//// NEXT FRAME:  table, bounce off walls, collisions, show all
void draw() {
  background( 150,200,250 );
  rectMode( CORNERS );
  table( west, north, east, south );
  bounce();
  collisions();
  show();
  messages();
  text( int(one.x), 10,10 );
  text( int(one.y), 10,20 );
}

//// SCENE:  draw the table with walls
void table( float west, float north, float east, float south ) {
  fill( 250, 250, 150 );    // green pool table
  strokeWeight(20);
  stroke( 0, 0, 200 );      // Brown walls
  rect( west-20, north-20, east+20, south+20 );
  stroke(0);
  strokeWeight(1);
}

//// ACTION:  bounce off walls, collisions
void bounce() {
  bamX += bamDX;  if ( bamX<west || bamX>east ) bamDX *= -1;
  bamY += bamDY;  if ( bamY<north || bamY>south ) bamDY *=  -1;

  debX += debDX;  if ( debX<west || debX>east ) debDX *= -1;
  debY += debDY;  if ( debY<north || debY>south ) debDY *=  -1;


  adamX += adamDX;  if ( adamX<west || adamX>east ) adamDX *= -1;
  adamY += adamDY;  if ( adamY<north || adamY>south ) adamDY *=  -1;

  crystalX += crystalDX;  if ( crystalX<west || crystalX>east ) crystalDX *= -1;
  crystalY += crystalDY;  if ( crystalY<north || crystalY>south ) crystalDY *=  -1;
  
  //// OBJECTS ////
  one.bounce();
  two.bounce();
  three.bounce();
  four.bounce();
  five.bounce();
}
void collisions() {
  float east,west;
  // Swap velocities!
  if ( dist( bamX,bamY, debX,debY ) < 30 ) {
    east=debDX;  debDX=bamDX;  bamDX=east;
    west=debDY;  debDY=bamDY;  bamDY=west;
  }
  if ( dist( bamX,bamY, adamX,adamY ) < 30 ) {
    float adamY=adamDX;  adamDX=bamDX;  bamDX=adamY;
    float debY=adamDY;  adamDY=bamDY;  bamDY=debY;
  }
  if ( dist( debX,debY, adamX,adamY ) < 30 ) {
    float bamX=adamDX;  adamDX=debDX;  debDX=bamX;
    float debY=adamDY;  adamDY=debDY;  debDY=debY;
  }
  // crystal ball hits R,Y,B.
  float tmp,north,south,bamX=0;
  if ( dist( crystalX,crystalY, bamX,bamY ) < 30 ) {
    north=bamDX;  bamDX=crystalDX;  crystalDX=north;
    south=bamDY;  bamDY=crystalDY;  crystalDY=south;
  }
  if ( dist( crystalX,crystalY, debX,debY ) < 30 ) {
    bamX=debDX;  debDX=crystalDX;  crystalDX=bamX;
    bamX=debDY;  debDY=crystalDY;  crystalDY=bamX;
  }
  if ( dist( crystalX,crystalY, adamX,adamY ) < 30 ) {
    tmp=adamDX;  adamDX=crystalDX;  crystalDX=tmp;
    tmp=adamDY;  adamDY=crystalDY;  crystalDY=tmp;
  }
}

//// SHOW:  balls, messages
void show() {
  fill( 150,0,0 );    one( bamX,bamY );
  fill( 0,150,0 );    one( debX,debY );
  fill( 255,0,255 );  one( adamX,adamY );
  fill( 0,255,255 );  one( crystalX,crystalY );
  //// OBJECTS ////
  one.show();
  two.show();
  three.show();
  four.show();
  five.show();
}
void one( float x, float y ) {
  ellipse( x,y, 30,30 );
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
  if (key == '+') { bamDX *=2; bamDY *=2; }
  if (key == '-') { bamDX /=2; bamDY /=2; }
}

void mousePressed() {
  //// hit the crystal ball -- distance = force
  float force=  dist( mouseX,mouseY, crystalX,crystalY ) / 20;
  strokeWeight( force );
  line( mouseX,mouseY, crystalX,crystalY );
  strokeWeight(1);
  // Jump away (twice).
  crystalDX=  (crystalX-mouseX) / 30;
  crystalDY=  (crystalY-mouseY) / 30;
}


//// OBJECTS ////
class Ball {
  float x,y, dx,dy;
  int r,g,b;
  int n;
  //// CONSTRUCTORS ////
  Ball() {
     x=  random( middle,east );   y=  random( north, south );
     dx=  random( 1,3 );   dy=  random( 1,3 );
     r= g= b= 127;
     // Next number;
     n=  number++;
  }
  Ball( int r, int g, int b ) {
     x=  random( middle,east );   y=  random( north, south );
     dx=  random( 1,3 );   dy=  random( 1,3 );
  // Colors
     this.r=r;
     this.g=g;
     this.b=b;
     // Next number;
     n=  number++;
  }
    
 
  //// METHODS ////
  void bounce() {
    x += dx;  if ( x<west || x>east ) dx *= -1;
    y += dy;  if ( y<north || y>south ) dy *=  -1;
  }
  void show() {
    fill( r,g,b );    
    ellipse( x,y, 30,30 );
    fill( (r+128)%256, (g+128)%256, (b+128)%256 );
    text( n, x-5, y+2 );
  }
}
  

