# 2-DDesignClass
Some 2-D design projects. Has basic giles in it right now, but i plan on creating some sort of algorithm to block out sensitive numbers or addresses in a picture.

Week of 9/8/2021
Created a basic black and white picture processing program, a program that writes words on to a basic java graphic window , a randomizer for an image 
(just randomizes the picture pixels), and an attempt to create a program that makes a picture whiter/brighter while also creating a black border around the image.
Goals for next week
  - Attempt to fix my border issue. Bottom right of m border is non-existant
  - Attempt to modify my algorithm so that it not just whitens, but actually just brightenss the image.
  - Start looking at blurring an image and attempt to code that so that I can move forward in the project

Week of 9/15/21
Created a very generic Gaussian filter to blur an image. Right now it is very unoptamized and runs very very slow, especially on bigger images. I also created a little program that shifts the color black to the color neon green in an image, by basically detecting colors and rewriting over the pixel
Goals for next week
  - Make use of some of my data structures knowledge to make sure im not using so many nested for loops (this is making my algorithm go super slow)
  - Make the Gaussian Blur more intense than what it is now
  - Look at starter code edge detection to continue my project. Lucky for me edge detection can be implemented by using the Guassian formula first!

Week of 9/22/21
Made my Guassian filter a little bit more quick, mainly by removing some print statements and limiting the amount of 2-D array created throughout the algorithm. Created a mean blur algorithm to show that algorithm and compare it to a guassian blur. Played around with my meanblur class to see if i could get randmo values in there aswell, but with no avail.Researched into edge detection and found out about Sobel Operator. In order to create this I will need to do a few things. They go as follows:
  1) Grayscale the image first
  2) Use the Guassain Blur to make edges more apperant.
  3) Process the imgae with a Sobel edge detector.
    a) Create a basic kernal with the values -1 0 1       and -1 -2 -1
                                             -2 0 2            0  0  0 
                                             -1 0 1            1  2  1
    b) use these to edge detect
    c) To find the total of these use trig to find hypotenuse and combine values to one image
    d) Find angle of the edge using trig atan(gradienty/gradientx) to add color and add orentaiton to the image!


Goals for next week
 - Implement an algorithm for the Sobel edge detection algorithm.

