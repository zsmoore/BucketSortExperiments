import sys
import json
import time

MILEAGE = 50
END_MILE = 10000

''' n solution '''

def load_list(input_path):

    with open(input_path) as in_f:
        return json.load(in_f)


def gen_buckets(mileage_list):
    buckets = {}
    for station in mileage_list:
        dist = station // MILEAGE
        if dist in buckets:
            buckets[dist].append(station)
        else:
            buckets[dist] = [station]

    return buckets


def get_gas_stations(mileage_list):

    buckets = gen_buckets(mileage_list)

    output_route = []
    curr_distance = 0
    curr_bucket = 0
    while curr_distance != END_MILE:
        max_station = curr_distance
        for station in buckets[curr_bucket]:
            if station < curr_distance + MILEAGE and station > max_station:
                max_station = station

        if max_station == curr_distance:
            for station in buckets[curr_bucket - 1]:
                if station > max_station:
                    max_station = station
        else:
            curr_bucket += 1

        curr_distance = max_station
        output_route.append(max_station)

    return output_route


def main():
    if len(sys.argv) != 2:
        print('Incorrect number of args, requires input file')
        exit()

    input_list = load_list(sys.argv[1])

    start = time.time()
    output_list = get_gas_stations(input_list)
    end = time.time()
    print(output_list)
    print('Solution took:')
    print(end - start)


if __name__ == '__main__':
    main()
