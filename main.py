import cv2
import numpy as np
prevCircle = None
circ1 = None
dist = lambda x1, y1, x2, y2: (x1-x2)**2 + (y1-y2)**2
deltax = 0
deltay = 0
deltas = 0
deltalist = {}


cap = cv2.VideoCapture("backview.MOV")


object_detector = cv2.createBackgroundSubtractorMOG2(history=1000, varThreshold=10)
framenum = 0
while True:
    framenum+=1
    ret, frame = cap.read()
    height, width, _ = frame.shape


    # 1. Object Detection
    objectmask = object_detector.apply(frame)
    _, objectmask = cv2.threshold(objectmask, 254, 255, cv2.THRESH_BINARY)
    nlabels, labels, stats, centroids = cv2.connectedComponentsWithStats(objectmask, None, None, None, 8, cv2.CV_32S)
    areas = stats[1:, cv2.CC_STAT_AREA]

    result = np.zeros((labels.shape), np.uint8)

    for i in range(0, nlabels - 1):
        if areas[i] >= 100:
            result[labels == i + 1] = 255
    objectmask = result
    lower_white = np.array([0,0,80])
    upper_white = np.array([255,75,255])
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    colormask = cv2.inRange(hsv, lower_white, upper_white)
    res = cv2.bitwise_and(frame, frame, mask = objectmask)
    res = cv2.bitwise_and(res, res, mask = colormask)
    cv2.imshow("objMask", objectmask)
    cv2.imshow('colormask', colormask)
    frame = res
    key = cv2.waitKey(30)
    grayFrame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    cv2.imshow('grey', grayFrame)
    blurFrame = cv2.GaussianBlur(grayFrame, (17,17),0)
    cv2.imshow('bin', frame)
    circles = cv2.HoughCircles(blurFrame, cv2.HOUGH_GRADIENT, 1, 500, param1=50, param2=30, minRadius=1, maxRadius=25)
    chosen = None
    if circles is not None:
        circles = np.uint16(np.around(circles))
        chosen = None
        for i in circles[0, :]:
            if prevCircle is None: chosen = i
            if prevCircle is not None:

                if dist(chosen[0], chosen[1], i[0], i[1])<+ dist(chosen[0], chosen[1], prevCircle[0], prevCircle[1]):
                    chosen = i
                    print(chosen[0], chosen[1])
        cv2.circle(frame, (chosen[0], chosen[1]), 1, (0, 100, 100), 3)
        cv2.circle(frame, (chosen[0], chosen[1]), chosen[2], (255, 0, 255), 3)
    if (circ1 is not None) and (chosen is not None):
        if (circ1[0] != chosen[0]) or (circ1[1] != chosen[1]) or (circ1[2] != chosen[2]):
            deltax = int(chosen[0]) - int(circ1[0])
            deltay = int(chosen[1]) - int(circ1[1])
            deltas = int(chosen[2]) - int(circ1[2])

            if deltas<50 and deltas>-50 and deltax > -50 and deltax < 50 and deltay < 50 and deltay > -50:
                deltalist[framenum] = [[int(chosen[0]), int(chosen[1]), int(chosen[2])], [deltax, deltay, deltas]]
                print(framenum)
                print(deltalist[framenum])
                with open("sig_frames.txt", 'a') as sigframes:
                    output_text = str(framenum) + ', ' + str(deltalist[framenum])
                    sigframes.write(output_text)
                    sigframes.write('\n')
                bbox = (int(chosen[0])-int(chosen[2]), int(chosen[1])-int(chosen[2]), int(chosen[0])+int(chosen[2]), int(chosen[1])+int(chosen[2]))
    elif circ1 is not None:

        cv2.circle(frame, (circ1[0], circ1[1]), 1, (0, 100, 100), 3)
        cv2.circle(frame, (circ1[0], circ1[1]), circ1[2], (255, 0, 255), 3)
        circ1[0] += deltax
        circ1[1] += deltay
        circ1[2] += deltas

    if chosen is not None:
        circ1 = chosen



    cv2.imshow("circles", frame)

cap.release()
cv2.destroyAllWindows()
print(deltalist)