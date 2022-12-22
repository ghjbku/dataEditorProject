package saveDataEditor.ItemEntities;

public interface ItemEntity {
     long id=0;
     String name="";
     Long amount=1L;
     Long price=0L;
     Long age = 0L;
     Double quality = 0d;

      long getId();
      String getName();
      Long getAmount();
      Long getPrice();
      Long getAge();
      Double getQuality();
}
