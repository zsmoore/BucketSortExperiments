import pickle
import sys
import random
import json

END_MILE = 10000
MILEAGE = 50
REQUIRED_STOPS = 10000 / 50

def gen_list(num_elems):

    out_list = [END_MILE]
    for i in range(num_elems - 1):
        out_list.append(random.randrange(0, END_MILE))

    return out_list


def get_list(num_elems):
    
    out_list = gen_list(num_elems)

    num_tries = 1
    while not is_list_valid(out_list):
        out_list = gen_list(num_elems)
        num_tries += 1       

    print('Found list after {0} generations'.format(num_tries))
    return out_list


def is_list_valid(alist):

    alist = sorted(alist)
    current_mile = 0
    for num in alist:
        if num - current_mile > MILEAGE:
            return False
        current_mile = num

    if END_MILE - current_mile > MILEAGE:
        return False

    return True


def main():

    if len(sys.argv) != 2:
        print('Incorrect number of args, required length of list output')
        exit()
    
    list_length = int(sys.argv[1])
    if (list_length < REQUIRED_STOPS):
        print('Unable to make list, required atleast a length of {0}'.format(REQUIRED_STOPS))
        exit()

    out_list = get_list(list_length)
    with open('mileage_marker_list.json', 'w') as out_file:
        json.dump(out_list, out_file)

  
if __name__ == '__main__':
    main()
