//// x5force.java:  Weird code -- WARNING:  DO NOT COPY ANY OF THIS CODE!
//// (Assume ball diameter of 30.)

//// GLOBALS:  pool table, 3 colored balls

String title=  "ELASTIC COLLISIONS  (x5force.java)";
String help=   "Click to shoot crystal ball. \n 'r' key to reset, 'q to quit.";
String news=help;
String author=  "Luke Skywalker";
//  WARNING:  DO NOT COPY ANY OF THIS CODE!

float west, east, north, south;
float middle;

float crystalX,  crystalY,  crystalDX,  crystalDY;
float bamX,  bamY,  bamDX,  bamDY;
float debX,  debY,  debDX,  debDY;
float adamX, adamY, adamDX, adamDY;   //  WARNING:  DO NOT COPY ANY OF THIS CODE!

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
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
   // Random positions.
   bamX=  random( middle,east );   bamY=  random( north, south );
   debX=  random( middle,east );   debY=  random( north, south );
   adamX=  random( middle,east );   adamY=  random( north, south );
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
   // Random speeds
   bamDX=  random( 1,3 );   bamDY=  random( 1,3 );
   debDX=  random( 1,3 );   bamDY=  random( 1,3 );
   adamDX=  random( 1,3 );   adamDY=  random( 1,3 );   
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
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
  debX += debDX;  if ( debX<west || debX>east ) debDX *= -1;
  debY += debDY;  if ( debY<north || debY>south ) debDY *=  -1;
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
  adamX += adamDX;  if ( adamX<west || adamX>east ) adamDX *= -1;
  adamY += adamDY;  if ( adamY<north || adamY>south ) adamDY *=  -1;
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
  crystalX += crystalDX;  if ( crystalX<west || crystalX>east ) crystalDX *= -1;
  crystalY += crystalDY;  if ( crystalY<north || crystalY>south ) crystalDY *=  -1;
}
void collisions() {
  float east,west;
  // Swap velocities!
  if ( dist( bamX,bamY, debX,debY ) < 30 ) {
    east=debDX;  debDX=bamDX;  bamDX=east;
    west=debDY;  debDY=bamDY;  bamDY=west;
  }
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!

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
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
  if ( dist( crystalX,crystalY, debX,debY ) < 29 ) {
    bamX=debDX;  debDX=crystalDX;  crystalDX=bamX;
    bamX=debDY;  debDY=crystalDY;  crystalDY=bamX;
  }
  if ( dist( crystalX,crystalY, adamX,adamY ) < 31 ) {
    tmp=adamDX;  adamDX=crystalDX;  crystalDX=tmp;
    tmp=adamDY;  adamDY=crystalDY;  crystalDY=tmp;
  }
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
}

//// SHOW:  balls, messages
void show() {
  fill( 150,0,0 );    ellipse( bamX,bamY, 30,29.99 );
  fill( 0,150,0 );  ellipse( debX,debY, 30,30 );
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
  fill( 255,0,255 );    ellipse( adamX,adamY, 30,30 );
  fill( 0,255,255 );    ellipse( crystalX,crystalY, 30.07,30 );
//  WARNING:  DO NOT COPY ANY OF THIS CODE!
}
void messages() {
  fill(0);
  text( title, width/3, 20 );
  text( news, width/3, 42 );
  text( author, 10, height-11 );
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
  //
  //  WARNING:  DO NOT COPY ANY OF THIS CODE!
  crystalDX=  (crystalX-mouseX) / 28.9;
  crystalDY=  (crystalY-mouseY) / 31.2;
}


