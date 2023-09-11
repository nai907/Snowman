def initial_state():
    return (8, 0, 0)

def is_goal(s):
    return s[0] == 4 and s[1] == 4

def successors(s):
    x, y, z = s
    # Try to pour from one to another
    # 12 21 13 31 23 32
    t = 5-y # check how much to pour in bottle 2
    if x>0 and t>0: # pour bottle 1 to bottle 2
      if x>t:
        yield ((x-t,5,z),t)
      else:
        yield ((0,y+x,z),x)
    t = 8-x
    if y>0 and t>0: # pour bottle 2 to bottle 1
      if y>t:
        yield ((8,y-t,z),t)
      else:
        yield ((x+y,0,z),y)
    t = 3-z
    if x>0 and t>0: # pour bottle 1 to bottle 3
      if x>t: # if x is enough
        yield ((x-t,y,3),t)
      else: # if x is not enough
        yield ((0,y,x+z),x)
    t = 8-x
    if z>0 and t>0: # pour bottle 3 to bottle 1
      if z>t:
        yield ((8,y,z-t),t)
      else:
        yield ((x+z,y,0),z)
    t = 3-z
    if y>0 and t>0: # pour bottle 2 to bottle 3
      if y>t:
        yield ((x,y-t,3),t)
      else:
        yield ((x,0,z+y),y)
    # pour bottle 3 to bottle 2
    t = 5-y  # check how much to pour in bottle 2
    if z>0 and t>0:
      if z>t:
        yield ((x,5,z-t),t)
      else:
        yield ((x,y+z,0),z)
