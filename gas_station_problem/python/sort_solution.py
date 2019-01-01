import sys
import json
import time

MILEAGE = 50
END_MILE = 10000

''' nlogn solution '''

def load_list(input_path):

    with open(input_path) as in_f:
        return json.load(in_f)


def get_gas_stations(mileage_list):

    sorted_list = sorted(mileage_list)
    out_list = []

    current_mile = 0
    last_station = 0
    for station in sorted_list:
        if station - current_mile >= MILEAGE:
            out_list.append(last_station)
            current_mile = last_station
        else:
            last_station = station

        if station == END_MILE:
            out_list.append(station)
            break
  
    return out_list


def main():
    if len(sys.argv) != 2:
        print('Incorrect number of args, requires input file')
        exit()

    input_list = load_list(sys.argv[1])
    
    start = time.time()
    output_solution = get_gas_stations(input_list)
    end = time.time()
    print(output_solution)
    print('Solution took:')
    print(end - start)


if __name__ == '__main__':
    main()

