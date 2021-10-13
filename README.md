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

Week of 9/29/21
Implemented the Sobel Edge Detector algorithm. I managed to get everything, but one thing in my Sobel algorithm, which was the angle to get a hind of color to give me more information to work with. Regarldess, the Sobel algorithm works and there are defined edges for my pictures that are put into it! I also took a look at the canny edge detector which I will work on to get an even better Edge detector.
Goals for next week
  - Implement the agnle piece in my sobel algorithm to get color into my images and give them depth
  - Look at the Canny Edge Detector and determine what needs to be done to impliment if not implement it.

Week of 10/06/21
Looked at the histogram class, and created a way to count the amount of pixels within an already processed sobel
picture. The class whitetoRed.java converts all rgb values greater thatn 15 (so the outlines from the sobel) into red and then calculates the amount of times it detects that condition. This then let me compare the impact of the
guassian radius applied to the amount of edge that the sobel ends up. I did this in the sobel edge driver class where
i tested the radius 4, raidus 9, and raidus 25. While increasing the radius does indeed decrease the amount of edges,
too big of radius increases the amount to process the gaussain blur (bigger kernal) and it negativly impacts what
the sobel edge algorithm can see (not enough edges). I looked at the canny edge detector and it did not go so well
so I will save that for next week. I was also not able to implement the arctan piece in the sobel algorithm
Goals for next Week:
  - Implement the canny edge detector algorithm
  - Implement a way to start detecting letters on my images.
Week of 10/13/21
Created a master driver class for all of my image processing programs so that i can play around much more easiy with images and different processes.
Also, looked in to CNNs (convoluted network nodes) to start playing with that as well as deep learning to start creating some sort of algorithm that can detect plates and
censor them. Although there are libraries that can detect letters and do that, but I want to look into deep learning and CNN's as they seem interestings
Goals for next week
  - Implement a very simple CNN 
  - Look into image segmentation and start doing some work to identify how i can detect car plates
