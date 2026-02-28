package com.example.scalatraplayground.dao

import org.specs2.mutable.Specification
import com.example.scalatraplayground.entity.Page

class PageDaoSpec extends Specification {

    "PageDao" should {

        "contain exactly two pages" in {
            PageDao.pages must have size 2
        }

        "contain page1 with bacon-ipsum slug" in {
            val page1 = PageDao.page1

            page1.slug must_== "bacon-ipsum"
            page1.title must_== "Bacon ipsum dolor sit amet hamburger"
            page1.summary must_== "A sample of bacon-flavored placeholder text for meat lovers."
            page1.body must contain("Shankle pancetta turkey")
            page1.body must contain("Short ribs enim")
        }

        "contain page2 with veggie-ipsum slug" in {
            val page2 = PageDao.page2

            page2.slug must_== "veggie-ipsum"
            page2.title must_== "Arugula prairie turnip desert raisin sierra leone"
            page2.summary must_== "A sample of vegetable-themed placeholder text for plant-based enthusiasts."
            page2.body must contain("Veggies sunt bona vobis")
            page2.body must contain("Brussels sprout mustard")
        }

        "have pages list containing both page1 and page2" in {
            val pages = PageDao.pages

            pages must contain(PageDao.page1)
            pages must contain(PageDao.page2)
        }

        "allow finding page by slug" in {
            val foundBacon = PageDao.pages.find(_.slug == "bacon-ipsum")
            val foundVeggie = PageDao.pages.find(_.slug == "veggie-ipsum")
            val notFound = PageDao.pages.find(_.slug == "non-existent")

            foundBacon must beSome[Page]
            foundBacon.get.slug must_== "bacon-ipsum"

            foundVeggie must beSome[Page]
            foundVeggie.get.slug must_== "veggie-ipsum"

            notFound must beNone
        }

        "have pages with non-empty content" in {
            PageDao.pages.forall(page =>
                page.slug.nonEmpty &&
                    page.title.nonEmpty &&
                    page.summary.nonEmpty &&
                    page.body.nonEmpty
            ) must beTrue
        }

        "have unique slugs for all pages" in {
            val slugs = PageDao.pages.map(_.slug)
            slugs.distinct must have size slugs.size
        }
    }
}
