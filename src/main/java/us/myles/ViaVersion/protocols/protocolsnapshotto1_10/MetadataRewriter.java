package us.myles.ViaVersion.protocols.protocolsnapshotto1_10;

import com.google.common.base.Optional;
import us.myles.ViaVersion.api.minecraft.metadata.Metadata;
import us.myles.ViaVersion.api.type.Type;
import us.myles.ViaVersion.protocols.protocol1_9to1_8.metadata.NewType;

import java.util.List;

public class MetadataRewriter {
    public static int rewriteEntityType(int currentType, List<Metadata> metadata) {
        if (currentType == 68) {
            // ElderGuardian - 4
            Optional<Metadata> options = getById(metadata, 12);
            if (options.isPresent()) {
                if ((((byte) options.get().getValue()) & 0x04) == 0x04) {
                    return 4;
                }
            }
        }
        if (currentType == 51) {
            // WitherSkeleton - 5
            // Stray - 6
            Optional<Metadata> options = getById(metadata, 12);
            if (options.isPresent()) {
                if (((int) options.get().getValue()) == 1) {
                    return 5;
                }
                if (((int) options.get().getValue()) == 2) {
                    return 6;
                }
            }
        }
        if (currentType == 54) {
            // ZombieVillager - 27
            // Husk - 23
            Optional<Metadata> options = getById(metadata, 13);
            if (options.isPresent()) {
                int value = (int) options.get().getValue();
                if (value > 0 && value < 6) {
                    metadata.add(new Metadata(16, NewType.VarInt.getTypeID(), Type.VAR_INT, value - 1)); // Add profession type to new metadata
                    return 27;
                }
                if (value == 6) {
                    return 23;
                }
            }
        }
        if (currentType == 100) {
            // SkeletonHorse - 28
            // ZombieHorse - 29
            // Donkey - 31
            // Mule - 32
            Optional<Metadata> options = getById(metadata, 14);
            if (options.isPresent()) {
                if (((int) options.get().getValue()) == 0) {
                    return currentType;
                }
                if (((int) options.get().getValue()) == 1) {
                    return 31;
                }
                if (((int) options.get().getValue()) == 2) {
                    return 32;
                }
                if (((int) options.get().getValue()) == 3) {
                    return 29;
                }
                if (((int) options.get().getValue()) == 4) {
                    return 28;
                }
            }
        }
        return currentType;
    }

    public static Optional<Metadata> getById(List<Metadata> metadatas, int id) {
        for (Metadata metadata : metadatas) {
            if (metadata.getId() == id) return Optional.of(metadata);
        }
        return Optional.absent();
    }
}
