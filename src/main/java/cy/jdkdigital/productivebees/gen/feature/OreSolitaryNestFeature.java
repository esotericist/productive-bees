package cy.jdkdigital.productivebees.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class OreSolitaryNestFeature extends SolitaryNestFeature
{
    private final float probability;
    private int yMin;
    private int yMax;

    public OreSolitaryNestFeature(float probability, Codec<ReplaceBlockConfig> configFactory) {
        this(probability, configFactory, 0, 64);
    }

    public OreSolitaryNestFeature(float probability, Codec<ReplaceBlockConfig> configFactory, int yMin, int yMax) {
        super(probability, configFactory);
        this.probability = probability;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    @Override
    public boolean place(@Nonnull ISeedReader world, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random rand, @Nonnull BlockPos blockPos, @Nonnull ReplaceBlockConfig featureConfig) {
        if (nestShouldNotGenerate(featureConfig) || rand.nextFloat() > this.probability) {
            return false;
        }

        // Get random block in chunk
        blockPos = blockPos.south(rand.nextInt(14)).east(rand.nextInt(14));

        // Go to yMin
        blockPos = blockPos.above(yMin);

        BlockStateMatcher matcher = BlockStateMatcher.forBlock(featureConfig.target.getBlock());
        while (blockPos.getY() < yMax) {
            blockPos = blockPos.above(2);
            if (matcher.test(world.getBlockState(blockPos))) {
                // Find air
                int d = 3;
                List<BlockPos> blockList = BlockPos.betweenClosedStream(blockPos.offset(-d, -d, -d), blockPos.offset(d, d, d)).map(BlockPos::immutable).collect(Collectors.toList());
                for (BlockPos pos : blockList) {
                    if (world.isEmptyBlock(pos)) {
                        // Find block around that air pos
                        List<BlockPos> aroundAir = BlockPos.betweenClosedStream(pos.offset(-1, -1, -1), pos.offset(1, 1, 1)).map(BlockPos::immutable).collect(Collectors.toList());
                        for (BlockPos airPos : aroundAir) {
                            if (matcher.test(world.getBlockState(airPos))) {
                                placeNest(world, blockPos, featureConfig);
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
