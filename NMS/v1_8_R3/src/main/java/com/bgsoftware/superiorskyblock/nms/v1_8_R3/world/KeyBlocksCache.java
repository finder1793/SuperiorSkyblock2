package com.bgsoftware.superiorskyblock.nms.v1_8_R3.world;

import com.bgsoftware.superiorskyblock.api.key.Key;
import com.bgsoftware.superiorskyblock.core.key.KeyImpl;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.IBlockData;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;

import java.util.IdentityHashMap;
import java.util.Map;

public class KeyBlocksCache {

    private static final Map<IBlockData, Key> BLOCK_TO_KEY = new IdentityHashMap<>();

    private KeyBlocksCache() {

    }

    public static Key getBlockKey(IBlockData blockData) {
        return BLOCK_TO_KEY.computeIfAbsent(blockData, unused -> {
            Block block = blockData.getBlock();
            Material blockType = CraftMagicNumbers.getMaterial(block);
            byte data = (byte) block.toLegacyData(blockData);
            return KeyImpl.of(blockType, data);
        });
    }

    public static void cacheAllBlocks() {
        Block.d.forEach(KeyBlocksCache::getBlockKey);
    }

}
