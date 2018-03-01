

def ascii2Hex(s):
    list_h = []
    for c in s:
        list_h.append(str(hex(ord(c)))[-2:])
    return ''.join(list_h)

def hex2Byte(h):
    return bytes.fromhex(h)

def hex2ascii(hex_str):
    a = []
    for i in range(0, len(hex_str), 2):
        b = hex_str[i:i+2]
        a.append(chr(int(b, 16)))
    return ''.join(a)

def byte2Hex(bins):
    return ' '.join(["%02X" % x for x in bins])