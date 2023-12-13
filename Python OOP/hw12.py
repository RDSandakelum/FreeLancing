class Item: 
    '''
    Purpose: This class represents a single clothing item in a clothing store
    Instance variables: 
        name : name of the item 
        price: price of the item
        category: category of the item (Head, Torso, Legs, Feet)
        store: store name of the store this items belongs to
    Methods: 
        __init__ : initialize an item instance
        __str__ : return a string representation of the item
        __lt__ : compare the price of two items
        getters and setters : accessing the instance variables from outside the class and change them.
    '''

    def __init__(self, csv_string, store):
        name, price_str, category = csv_string.split(',')

        self.name = name.strip()
        self.price = float(price_str.strip())
        self.category = category.strip()
        self.store = store 

    def __str__(self):
        return f"{self.name} ({self.category}): ${self.price}"
    
    def __lt__(self, other_item):
        return self.price < other_item.price
    
    def get_name(self):
        return self.name 
    
    def set_name(self, name):
        self.name = name 

    def get_price(self):
        return self.price
    
    def set_price(self, price):
        self.price = float(price)

    def get_categoty(self):
        return self.categoty
    
    def set_categoty(self, categoty):
        self.categoty = categoty

    def get_store(self):
        return self.store
    
    def set_store(self, store):
        self.store = store



class Store:

    '''
    Purpose: This class represents a clothing store in the mall
    Instance variables: 
        name : name of the store 
        items: All the items in the store
    Methods: 
        __init__ : initialize an store instance
        __str__ : return a string representation of the store (print out all the items in the store)
    '''

    def __init__(self, name, filename):
        self.name = name 

        self.items = []

        with open(filename, 'r') as file:
            file.readline()
            for line in file:
                item_data = line.strip()
                item = Item(item_data, self.name)
                self.items.append(item)

    def __str__(self):
        store_str = f"{self.name}\n"
        item_str_list = [str(item) for item in self.items]
        store_str += "\n".join(item_str_list)
        return store_str
    

def cheap_outfit(stores_list):

    cheapest_items = {'Head': None, 'Torso': None, 'Legs': None, 'Feet': None}

    for category in cheapest_items.keys():
        category_items = []

        for store in stores_list:
            for item in store.items:
                if item.category == category:
                    category_items.append(item)

        cheapest_item = min(category_items)

        cheapest_items[category] = cheapest_item

    return cheapest_items
