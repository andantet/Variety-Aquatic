package org.variety.variety_aquatic.Entities.client;

import org.variety.variety_aquatic.Entities.Variant.BettaVariant;

public interface IVariantEntity<T extends IVariant> {
    T getVariant();
}
