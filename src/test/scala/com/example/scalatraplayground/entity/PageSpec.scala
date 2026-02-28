package com.example.scalatraplayground.entity

import org.specs2.mutable.Specification

class PageSpec extends Specification {

    "Page entity" should {

        "be created with all fields" in {
            val page = Page(
                slug = "test-slug",
                title = "Test Title",
                summary = "Test summary",
                body = "Test body content"
            )

            page.slug must_== "test-slug"
            page.title must_== "Test Title"
            page.summary must_== "Test summary"
            page.body must_== "Test body content"
        }

        "support copy method" in {
            val originalPage = Page(
                slug = "original",
                title = "Original Title",
                summary = "Original summary",
                body = "Original body"
            )

            val modifiedPage = originalPage.copy(title = "Modified Title")

            modifiedPage.slug must_== "original"
            modifiedPage.title must_== "Modified Title"
            modifiedPage.summary must_== "Original summary"
            modifiedPage.body must_== "Original body"
        }

        "support equality comparison" in {
            val page1 = Page("slug", "title", "summary", "body")
            val page2 = Page("slug", "title", "summary", "body")
            val page3 = Page("different", "title", "summary", "body")

            page1 must_== page2
            page1 must_!= page3
        }

        "have proper toString representation" in {
            val page = Page("test-slug", "Test Title", "Summary", "Body")
            val stringRep = page.toString

            stringRep must contain("test-slug")
            stringRep must contain("Test Title")
            stringRep must contain("Summary")
            stringRep must contain("Body")
        }
    }
}
