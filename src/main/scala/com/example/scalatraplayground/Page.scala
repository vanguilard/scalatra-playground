package com.example.scalatraplayground

case class Page(slug: String, title: String, summary: String, body: String)

object PageDao {
    val page1 = Page(
        slug = "bacon-ipsum",
        title = "Bacon ipsum dolor sit amet hamburger",
        summary = "A sample of bacon-flavored placeholder text for meat lovers.",
        body = """Shankle pancetta turkey ullamco exercitation laborum ut officia corned beef voluptate.
      |Fugiat mollit, spare ribs pork belly flank voluptate ground round do sunt laboris jowl.
      |Meatloaf excepteur hamburger pork chop fatback drumstick frankfurter pork aliqua.
      |Pork belly meatball meatloaf labore. Exercitation commodo nisi shank, beef drumstick duis.
      |Venison eu shankle sunt commodo short loin dolore chicken prosciutto beef swine elit quis beef ribs.
      |
      |Short ribs enim shankle ribeye andouille bresaola corned beef jowl ut beef.
      |Tempor do boudin, pariatur nisi biltong id elit dolore non sunt proident sed.
      |Boudin consectetur jowl ut dolor sunt consequat tempor pork chop capicola pastrami mollit short loin.""".stripMargin
    )

    val page2 = Page(
        slug = "veggie-ipsum",
        title = "Arugula prairie turnip desert raisin sierra leone",
        summary = "A sample of vegetable-themed placeholder text for plant-based enthusiasts.",
        body = """Veggies sunt bona vobis, proinde vos postulo esse magis napa cabbage beetroot dandelion radicchio.
      |Brussels sprout mustard salad jicama grape nori chickpea dulce tatsoi.
      |Maize broccoli rabe collard greens jicama wattle seed nori garbanzo epazote coriander mustard.""".stripMargin
    )

    val pages: List[Page] = List(page1, page2)
}
