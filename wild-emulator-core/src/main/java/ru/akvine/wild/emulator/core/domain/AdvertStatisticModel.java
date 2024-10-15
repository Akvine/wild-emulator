package ru.akvine.wild.emulator.core.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.domain.base.SoftModel;
import ru.akvine.wild.emulator.core.repositories.entities.AdvertStatisticEntity;

@Data
@Accessors(chain = true)
public class AdvertStatisticModel extends SoftModel {
    private Long id;
    private int views;
    private int clicks;
    private double ctr;
    private double cpc;
    private int sum;
    private int atbs;
    private int orders;
    private int cr;
    private int shks;
    private int sumPrice;
    private AdvertModel advert;

    public AdvertStatisticModel(AdvertStatisticEntity advertStatistic) {
        this.id = advertStatistic.getId();
        this.views = advertStatistic.getViews();
        this.clicks = advertStatistic.getClicks();
        this.ctr = advertStatistic.getCtr();
        this.cr = advertStatistic.getCr();
        this.sum = advertStatistic.getSum();
        this.atbs = advertStatistic.getAtbs();
        this.orders = advertStatistic.getOrders();
        this.cpc = advertStatistic.getCpc();
        this.shks = advertStatistic.getShks();
        this.sumPrice = advertStatistic.getSumPrice();
        this.advert = new AdvertModel(advertStatistic.getAdvertEntity());

        this.createdDate = advertStatistic.getCreatedDate();
        this.updatedDate = advertStatistic.getUpdatedDate();
        this.deletedDate = advertStatistic.getDeletedDate();
        this.deleted = advertStatistic.isDeleted();
    }
}
